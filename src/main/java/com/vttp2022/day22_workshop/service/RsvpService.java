package com.vttp2022.day22_workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vttp2022.day22_workshop.repository.RsvpRepository;

@Service
public class RsvpService {

    @Autowired
    private RsvpRepository rsvpRepository;

    public boolean searchRsvp(String name) throws Exception {
        // return true if there is rsvp(s)
        return rsvpRepository.searchRsvp(name).size() > 0;
    }

}
