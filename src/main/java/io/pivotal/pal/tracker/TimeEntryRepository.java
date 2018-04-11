package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry timeEntry);
    public TimeEntry find(long id) ;
    public List<TimeEntry> list();
    public TimeEntry update(long id, TimeEntry timeEntry);
    public void delete(long id);

}



