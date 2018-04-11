package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> mapOfTimeEntry = new HashMap<>();

    public InMemoryTimeEntryRepository() {
        TimeEntry.idCounter = 0;
    }


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
            timeEntry.setId(id);
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
