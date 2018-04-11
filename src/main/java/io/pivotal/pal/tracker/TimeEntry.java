package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.Objects;

public class TimeEntry {

    public void setId(long id) {
        this.id = id;
    }

    private long id;
    private long projectId;

    public long getProjectId() {
        return projectId;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    private long userId;
    private LocalDate date;
    private int hours;

    static long idCounter = 0;

    public TimeEntry(long projectId, long userId, LocalDate dt, int hours ) {
        this.id = ++idCounter;

        this.projectId = projectId;
        this.userId = userId;
        this.date = dt;
        this.hours = hours;
    }

    public TimeEntry(long id, long projectId, long userId, LocalDate dt, int hours ) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = dt;
        this.hours = hours;
    }

    public TimeEntry() {

    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return  //id == timeEntry.id &&
                projectId == timeEntry.projectId &&
                userId == timeEntry.userId &&
                hours == timeEntry.hours &&
                Objects.equals(date, timeEntry.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, projectId, userId, date, hours);
    }
}
