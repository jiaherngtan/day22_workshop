DROP DATABASE IF EXISTS rsvpdb;

CREATE DATABASE rsvpdb;

USE rsvpdb;

CREATE TABLE rsvp(
    id CHAR(8) NOT NULL,
    name VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,
    phone VARCHAR(128) NOT NULL,
    confirmation_date DATE,
    comments TEXT,

    PRIMARY KEY(id)
);