package com.lowes.meetingapp.core.dao.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfficeFloorDO  {

    private Long floorNumber;
    private String floorName;
    private List<MeetingRoomDO> meetingRooms;


}
