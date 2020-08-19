package com.lowes.meetingapp.resources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MeetingAppResource {

    Logger logger=LoggerFactory.getLogger(MeetingAppResource.class);

    @GetMapping("/meetingApp")
    public String myMeetingApp(){
        logger.info("My first Application");
        return "Lowe's meeting app";
    }
}
