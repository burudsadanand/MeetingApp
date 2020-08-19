package com.lowes.meetingapp.resources;

import com.lowes.meetingapp.beans.request.SearchMeetingRequest;
import com.lowes.meetingapp.beans.response.SearchMeetingResponse;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.core.service.interfaces.ISearchScheduledMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchMeetingResource {

    @Autowired
    private ISearchScheduledMeetingService searchScheduledMeetingService;

    @PostMapping("/searchMeetings")
    public ResponseEntity<ResponseBean<SearchMeetingResponse>>
                findMeetings(@RequestBody  SearchMeetingRequest searchRequest){
        return new ResponseEntity<ResponseBean<SearchMeetingResponse>>(searchScheduledMeetingService.fetchMeetings(searchRequest),HttpStatus.OK);
    }
}
