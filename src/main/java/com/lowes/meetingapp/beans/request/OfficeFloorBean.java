package com.lowes.meetingapp.beans.request;

import com.lowes.meetingapp.core.dao.beans.MeetingRoomDO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfficeFloorBean {

    private Long floorNumber;
    private String floorName;
    private List<MeetingRoomBean> meetingRooms;
}
