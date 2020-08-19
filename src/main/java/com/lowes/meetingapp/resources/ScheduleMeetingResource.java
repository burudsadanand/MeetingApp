package com.lowes.meetingapp.resources;

import com.lowes.meetingapp.beans.request.MeetingRoomRequestBean;
import com.lowes.meetingapp.beans.response.MeetingResponseBean;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.core.service.interfaces.IScheduleMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleMeetingResource {

    @Autowired
    private IScheduleMeetingService scheduleMeetingService;

    @PostMapping("/scheduleMeeting")
    @ResponseBody
    public ResponseEntity<ResponseBean<MeetingResponseBean>> scheduleMeeting(@RequestBody  MeetingRoomRequestBean meetingRoomRequestBean){
        ResponseBean<MeetingResponseBean> scheduleMeetingResponseBean= scheduleMeetingService.schedule(meetingRoomRequestBean);
        return new ResponseEntity<>(scheduleMeetingResponseBean,HttpStatus.OK);
    }
}
