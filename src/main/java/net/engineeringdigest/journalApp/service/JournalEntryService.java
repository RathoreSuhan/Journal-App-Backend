package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        try{
            journalEntry.setDate(LocalDateTime.now());
            //Saving the journalEntry into the journal_entries collection of journaldb Database
            JournalEntry s = journalEntryRepository.save(journalEntry);

            //Saving the journalEntry id into the journalEntries List field of users collection of journaldb Database
            User user = userService.findByUsername(userName);
            user.getJournalEntries().add(s);

            //if user is already present in the users collection than user data will be updated
            userService.saveEntry(user);
        }catch(Exception e){
            log.error("Exception ", e);
        }
        journalEntryRepository.save(journalEntry);
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName) {
        User user = userService.findByUsername(userName);
        user.getJournalEntries().removeIf(j -> j.getId().equals(id));

        //if user is already present in the users collection than user data will be updated
        userService.saveEntry(user);

        journalEntryRepository.deleteById(id);
    }
}


//controller(for endpoints creation) ---> service(business logic) ---> repository(for database interaction)