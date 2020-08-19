package com.lowes.meetingapp;

import com.lowes.meetingapp.beans.request.MeetingRoomRequestBean;
import com.lowes.meetingapp.resources.ScheduleMeetingResource;
import org.junit.jupiter.api.Test;

public class ScheduleMeetingResourceTest {

    @Test
    public  void testScheduleMeeting(){
        ScheduleMeetingResource scheduleMeetingResource=new ScheduleMeetingResource();
        MeetingRoomRequestBean meetingRoomRequestBean=new MeetingRoomRequestBean();
        scheduleMeetingResource.scheduleMeeting(meetingRoomRequestBean);
    }
}
