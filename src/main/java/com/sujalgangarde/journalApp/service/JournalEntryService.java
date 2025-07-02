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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry entry, String username) {
        try{
            User user = userService.findByUsername(username);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(entry); // Save the entry to the database
            user.getJournalEntries().add(saved); // Add the saved entry to the user's journal entries
            userService.saveEntry(user); // Save the updated/created user with the new entry
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

    public void deleteEntryById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry) {
//        JournalEntry oldentry = getEntryById(myid).orElse(null);
//        if (oldentry != null) {
//            oldentry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldentry.getTitle());
//            oldentry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldentry.getContent());
//            saveEntry(oldentry, user);
//            return new ResponseEntity<>(oldentry, HttpStatus.OK);
//        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}




