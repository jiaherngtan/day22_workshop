package com.vttp2022.day22_workshop.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Rsvp {

    private String name;
    private String email;
    private String phone;
    private String confirmationDate;
    private String comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Rsvp [name=" + name + ", email=" + email + ", phone=" + phone + ", confirmationDate=" + confirmationDate
                + ", comments=" + comments + "]";
    }

    public static Rsvp createRsvp(SqlRowSet rs) {

        Rsvp rsvp = new Rsvp();
        rsvp.setName(rs.getString("name"));
        rsvp.setEmail(rs.getString("email"));
        rsvp.setPhone(rs.getString("phone"));
        rsvp.setConfirmationDate(rs.getString("confirmation_date"));
        rsvp.setComments(rs.getString("comments"));

        return rsvp;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", getName())
                .add("email", getEmail())
                .add("phone", getPhone())
                .add("confirmation_date", getConfirmationDate())
                .add("comments", getComments())
                .build();
    }

    public JsonObject toJson(String bodyContent) {
        return Json.createObjectBuilder()
                .add("result:", bodyContent)
                .add("name", getName())
                .add("email", getEmail())
                .add("phone", getPhone())
                .add("confirmation_date", getConfirmationDate())
                .add("comments", getComments())
                .build();
    }

}
