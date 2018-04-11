package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@Component
public class TimeEntryRepository {

    private Map<Long, TimeEntry> mapOfTimeEntry = new HashMap<>();

    public TimeEntry create(TimeEntry timeEntry) {

        mapOfTimeEntry.put(timeEntry.getId(), timeEntry);

        return timeEntry;
    }

    public TimeEntry find(long id) {
        return mapOfTimeEntry.get(id);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<>();
        list.addAll(mapOfTimeEntry.values() );
        return list;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

        if( mapOfTimeEntry.containsKey(id)) {
            mapOfTimeEntry.put(id, timeEntry);
        }
        else {
            throw new RuntimeException("timeEntry not found for id: " + id);
        }
        return timeEntry;
    }

    public void delete(long id) {
        mapOfTimeEntry.remove(id);

    }

}
*/
public interface TimeEntryRepository {
    public TimeEntry create(TimeEntry timeEntry);
    public TimeEntry find(long id) ;
    public List<TimeEntry> list();
    public TimeEntry update(long id, TimeEntry timeEntry);
    public void delete(long id);
}



