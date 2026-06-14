package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;


    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
        return true;
    }
    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @GetMapping("gid/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("/id/{dId}")
    public String deleteJournalEntryById(@PathVariable ObjectId dId) {
        try {
            journalEntryService.deleteById(dId);
            return "Deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PutMapping("id/{uId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry){
        return null;
    }
}
