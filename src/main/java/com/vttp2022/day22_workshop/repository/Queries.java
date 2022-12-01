package com.vttp2022.day22_workshop.repository;

public class Queries {

    // get all the Rsvps from the database
    public static final String SQL_GET_RSVP = "SELECT * FROM rsvp";

    // search for Rsvps by wildcard name
    public static final String SQL_GET_RSVP_BY_NAME = "SELECT * FROM rsvp WHERE name LIKE ?";

    // search for Rsvps by exact name
    public static final String SQL_GET_RSVP_BY_NAME_EXACT = "SELECT * FROM rsvp WHERE name = ?";

    // overwrite existing rsvp
    public static final String SQL_OVERWRITE_RSVP = "UPDATE rsvp SET email = ?, phone = ?, confirmation_date = ?, comments = ? WHERE name = ?";

    // create a new rsvp
    public static final String SQL_INSERT_RSVP = "INSERT INTO rsvp(name,email,phone,confirmation_date,comments) VALUES(?, ?, ?, ?, ?)";

    // update an existing rsvp email
    public static final String SQL_GET_RSVP_BY_EMAIL = "SELECT name FROM rsvp WHERE email = ?";
    public static final String SQL_UPDATE_RSVP = "UPDATE rsvp SET email = ? WHERE name = ?";

    // get rsvp counts
    public static final String SQL_GET_RSVP_COUNT = "SELECT COUNT(*) FROM rsvp";
}
