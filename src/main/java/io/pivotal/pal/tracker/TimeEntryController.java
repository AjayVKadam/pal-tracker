package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry>  create(@RequestBody TimeEntry timeEntry) {

        TimeEntry entry = timeEntryRepository.create(timeEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(entry);
    }


    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeEntryRepository.list();

        if(list!=null) {
            //If found

            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        else {
            //If not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping( "{id}")
    public ResponseEntity<TimeEntry> read (@PathVariable("id") long Id) {
        TimeEntry entry = timeEntryRepository.find(Id);
        if (entry != null ) {
            return ResponseEntity.status(HttpStatus.OK).body(entry);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PutMapping( "{id}" )
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry expected) {
        TimeEntry entry = timeEntryRepository.update(id,expected);
        if(entry!=null) {
            //If found

            return ResponseEntity.status(HttpStatus.OK).body(entry);
        }
        else {
            //If not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {

        timeEntryRepository.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
