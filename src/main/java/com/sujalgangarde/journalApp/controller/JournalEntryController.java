package com.sujalgangarde.journalApp.controller;

import com.sujalgangarde.journalApp.entity.JournalEntry;
import com.sujalgangarde.journalApp.entity.User;
import com.sujalgangarde.journalApp.service.JournalEntryService;
import com.sujalgangarde.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if (!all.isEmpty()) {
            return new ResponseEntity<List<JournalEntry>>(all, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No entries found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username) {
        try {
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<JournalEntry>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myid) {
        Optional<JournalEntry> entry = journalEntryService.getEntryById(myid);
        if(entry.isPresent()) {
            return new ResponseEntity<JournalEntry>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{username}/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid) {
        journalEntryService.deleteEntryById(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myid}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry) {
        return journalEntryService.updateEntryById(myid, newEntry);
    }

}
