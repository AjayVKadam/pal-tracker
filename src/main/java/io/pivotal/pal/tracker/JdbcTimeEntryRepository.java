package io.pivotal.pal.tracker;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JdbcTimeEntryRepository implements  TimeEntryRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTimeEntryRepository(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);

    }

    @Override
    public TimeEntry create(TimeEntry timeEntry)  {

        //PreparedStatement pStmt = jdbcTemplate.getDataSource().getConnection().prepareStatement("INSERT INTO time_entries (id, project_id, user_id, date, hours) VALUES (?, ?, ?, ?, ?)");
        StringBuilder  builder = new StringBuilder("INSERT INTO time_entries (id, project_id, user_id, date, hours) VALUES (");
        builder.append(timeEntry.getId()).append(" , ").append(timeEntry.getProjectId())
                .append(" ,").append(timeEntry.getUserId())
                .append(" , '").append(timeEntry.getDate()).append("'")
                .append(" , ").append(timeEntry.getHours()).append(")");


        // String sql = "INSERT INTO time_entries (id, project_id, user_id, date, hours) VALUES (1000, 123, 321, '2017-01-09', 8)";
        jdbcTemplate.execute(builder.toString());
        return  timeEntry;

    }

    @Override
    public TimeEntry find(long id) {

        try {
            Map<String, Object> foundEntry = jdbcTemplate.queryForMap("Select * from time_entries where id = ?", id);
            TimeEntry timeEntry = new TimeEntry((Long) foundEntry.get("id"),
                    (Long) foundEntry.get("project_id"),
                    (Long) foundEntry.get("user_id"),
                    ((java.sql.Date) foundEntry.get("date")).toLocalDate(),
                    (Integer) foundEntry.get("hours"));
            return timeEntry;
        }catch(EmptyResultDataAccessException exception){
            return null;
        }
    }



    @Override
    public List<TimeEntry> list() {
/*
        String sql = "SELECT ID, NAME, AGE FROM EMPLOYEE";
        List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
        for (Map<String, Object> row : list) {
            System.out.println(row.get("name"));
        }*/
        List<TimeEntry> entryList = new ArrayList<>();

        StringBuilder builder = new StringBuilder("Select * from time_entries");

        List<Map<String, Object>> list = jdbcTemplate.queryForList(builder.toString());
        for (Map<String, Object> row : list) {

            TimeEntry timeEntry = new TimeEntry((Long)row.get("id"), (Long)row.get("project_id"),
                    (Long)row.get("user_id"),
                    ((java.sql.Date) row.get("date")).toLocalDate(),
                    (Integer) row.get("hours"));
            entryList.add(timeEntry);
        }




        return entryList;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

/*
        UPDATE table_name
        SET column1 = value1, column2 = value2...., columnN = valueN
        WHERE [condition];
        project_id, user_id, date, hours*/

        StringBuilder  builder = new StringBuilder("UPDATE time_entries SET " );
        builder.append("project_id =" + timeEntry.getProjectId())
                .append(" ,").append("user_id =" + timeEntry.getUserId())
                .append(" , ").append("date = '" + timeEntry.getDate())
                .append("' , ").append("hours = "+ timeEntry.getHours()).append(" WHERE id = ").append(id);

        int rows = jdbcTemplate.update(builder.toString());
        timeEntry.setId(id);
        return timeEntry;
    }

    @Override
    public void delete(long id) {

        StringBuilder  builder = new StringBuilder("DELETE FROM time_entries WHERE id=" );
        builder.append(id);

        int rowCount = jdbcTemplate.update(builder.toString());

    }
}
