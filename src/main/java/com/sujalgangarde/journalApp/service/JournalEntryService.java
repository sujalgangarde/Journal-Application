package com.sujalgangarde.journalApp.service;

import com.sujalgangarde.journalApp.entity.JournalEntry;
import com.sujalgangarde.journalApp.entity.User;
import com.sujalgangarde.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry entry, String username) {
        try{
            User user = userService.findByUsername(username);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(entry); // Save the entry to the database
            user.getJournalEntries().add(saved); // Add the saved entry to the user's journal entries
            userService.saveUser(user); // Save the updated/created user with the new entry
        } catch (Exception e) {
            log.error("Exception ", e);
            throw new RuntimeException("Exception while saving journal entry", e);
        }
    }

    public void saveEntry(JournalEntry entry) {
        try{
            journalEntryRepository.save(entry); // Save the entry to the database
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteEntryById(ObjectId id, String username) {
        boolean removed = false;
        try {
            User user = userService.findByUsername(username);
            removed = user.getJournalEntries().removeIf(entry -> entry.getId().equals(id)); // Remove the entry from the user's journal entries
            if(removed) {
                userService.saveUser(user); // Save the updated user
                journalEntryRepository.deleteById(id); // Delete the entry from the database
            }
            return removed;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Exception while deleting journal entry", e);
        }
    }

    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry, String username) {
        User user = userService.findByUsername(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = getEntryById(myid);
            if(journalEntry.isPresent()) {
                JournalEntry oldentry = journalEntry.get();
                oldentry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldentry.getTitle());
                oldentry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldentry.getContent());
                saveEntry(oldentry);
                return new ResponseEntity<>(oldentry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<JournalEntry> findByUsername(String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return user.getJournalEntries();
        }
        return List.of(); // Return an empty list if the user is not found
    }

}




