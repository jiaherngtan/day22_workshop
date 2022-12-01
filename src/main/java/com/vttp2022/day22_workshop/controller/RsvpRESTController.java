package com.vttp2022.day22_workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vttp2022.day22_workshop.model.Rsvp;
import com.vttp2022.day22_workshop.repository.RsvpRepository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
public class RsvpRESTController {

    @Autowired
    private RsvpRepository rsvpRepository;

    // get all RSVPs
    @GetMapping(path = "/api/rsvps", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRsvps() {

        try {
            List<Rsvp> rsvpList = rsvpRepository.getAllRsvps();

            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Rsvp rsvp : rsvpList)
                arrBuilder.add(rsvp.toJson());
            JsonArray result = arrBuilder.build();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result.toString());

        } catch (Exception e) {
            String errMsg = "No RSVP result found...";
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errMsg);
        }
    }

    // search for RSVP
    @GetMapping(path = "/api/rsvp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchRsvpByName(
            @RequestParam String q) {

        System.out.println(q);

        try {
            List<Rsvp> rsvpList = rsvpRepository.searchRsvp(q);

            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Rsvp rsvp : rsvpList)
                arrBuilder.add(rsvp.toJson());
            JsonArray result = arrBuilder.build();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result.toString());

        } catch (Exception e) {
            String errMsg = "No RSVP result for " + q;
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errMsg);
        }
    }

    // add new RSVP
    @PostMapping(path = "/api/rsvp")
    public ResponseEntity<String> addRsvp(
            @RequestBody MultiValueMap<String, String> form,
            Model model) {

        String name = form.getFirst("name");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");
        String confirmationDate = form.getFirst("confirmationDate");
        String comments = form.getFirst("comments");

        Rsvp rsvp = new Rsvp();
        rsvp.setName(name);
        rsvp.setEmail(email);
        rsvp.setPhone(phone);
        rsvp.setConfirmationDate(confirmationDate);
        rsvp.setComments(comments);

        try {
            if (rsvpRepository.checkIfRsvpExists(name)) {
                System.out.println("to update an existing rsvp...");
                rsvpRepository.overwriteRsvp(rsvp);
                String bodyContent = "rsvp for " + name + " updated";
                JsonObject result = rsvp.toJson(bodyContent);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(result.toString());
            } else {
                System.out.println("to create new rsvp...");
                rsvpRepository.createRsvp(rsvp);
                String bodyContent = "rsvp for " + name + " created";
                JsonObject result = rsvp.toJson(bodyContent);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(result.toString());
            }
        } catch (Exception e) {
            String errMsg = "Unable to create or update RSVP for " + name;
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errMsg);
        }
    }

    // update RSVP
    @PutMapping(path = "/api/rsvp/{email}")
    public ResponseEntity<String> updateRsvp(
            @PathVariable String email) {
        try {
            Rsvp rsvp = rsvpRepository.updateRsvp(email);
            String name = rsvp.getName();
            String bodyContent = "rsvp email for " + name + "updated";
            JsonObject result = rsvp.toJson(bodyContent);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result.toString());
        } catch (Exception e) {
            String errMsg = "Unable to update RSVP email...";
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errMsg);
        }
    }

    // get number of RSVPs
    @GetMapping(path = "/api/rsvps/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRsvpCount() {

        try {
            int rsvpCount = rsvpRepository.getRsvpCounts();

            String result = "Total number of Rsvps = " + rsvpCount;

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);

        } catch (Exception e) {
            String errMsg = "No RSVP count found...";
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errMsg);
        }
    }

}
