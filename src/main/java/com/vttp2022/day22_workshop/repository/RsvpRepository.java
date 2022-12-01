package com.vttp2022.day22_workshop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.vttp2022.day22_workshop.model.Rsvp;

import static com.vttp2022.day22_workshop.repository.Queries.*;

import java.util.LinkedList;
import java.util.List;

@Repository
public class RsvpRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Rsvp> getAllRsvps() {

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(
                SQL_GET_RSVP);

        List<Rsvp> rsvpList = new LinkedList<>();
        while (rs.next()) {
            Rsvp rsvp = Rsvp.createRsvp(rs);
            rsvpList.add(rsvp);
        }
        return rsvpList;
    }

    public List<Rsvp> searchRsvp(String name) {

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(
                SQL_GET_RSVP_BY_NAME, "%%%s%%".formatted(name));

        List<Rsvp> rsvpList = new LinkedList<>();
        while (rs.next()) {
            Rsvp rsvp = Rsvp.createRsvp(rs);
            rsvpList.add(rsvp);
        }
        return rsvpList;
    }

    public boolean checkIfRsvpExists(String name) {
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_RSVP_BY_NAME_EXACT, name);
        return rs.next();
    }

    public void overwriteRsvp(Rsvp rsvp) {
        jdbcTemplate.update(SQL_OVERWRITE_RSVP,
                rsvp.getEmail(),
                rsvp.getPhone(),
                rsvp.getConfirmationDate(),
                rsvp.getComments(),
                rsvp.getName());
    }

    public void createRsvp(Rsvp rsvp) {
        System.out.println(rsvp.toString());
        jdbcTemplate.update(SQL_INSERT_RSVP,
                rsvp.getName(),
                rsvp.getEmail(),
                rsvp.getPhone(),
                rsvp.getConfirmationDate(),
                rsvp.getComments());
    }

    public Rsvp updateRsvp(String email) {
        String name = jdbcTemplate.queryForObject(
                SQL_GET_RSVP_BY_EMAIL,
                String.class,
                email);

        System.out.println(name);

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(
                SQL_GET_RSVP_BY_NAME_EXACT, name);

        Rsvp rsvp = new Rsvp();
        while (rs.next()) {
            rsvp = Rsvp.createRsvp(rs);
        }

        return rsvp;
    }

    public int getRsvpCounts() {
        return jdbcTemplate.queryForObject(
                SQL_GET_RSVP_COUNT, Integer.class);
    }

}
