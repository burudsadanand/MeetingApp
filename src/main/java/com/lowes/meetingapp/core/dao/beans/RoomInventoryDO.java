package com.lowes.meetingapp.core.dao.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomInventoryDO {

    private String roomInventoryId;
    private String roomName;
    private String roomId;
    private String officeId;
    private List<OfficeFloorDO> floors;



}
