package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private final CounterService counter;
    private final GaugeService gauge;
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository,
                               CounterService counter,
                               GaugeService gauge) {
        this.timeEntryRepository = timeEntryRepository;
        this.counter = counter;
        this.gauge = gauge;
    }

    @PostMapping
    public ResponseEntity<TimeEntry>  create(@RequestBody TimeEntry timeEntry) {

        TimeEntry entry = timeEntryRepository.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        return ResponseEntity.status(HttpStatus.CREATED).body(entry);
    }


    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeEntryRepository.list();

        if(list!=null) {
            //If found
            counter.increment("TimeEntry.listed");
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
            counter.increment("TimeEntry.read");
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
            counter.increment("TimeEntry.updated");
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
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
