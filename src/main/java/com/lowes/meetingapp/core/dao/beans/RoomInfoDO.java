package com.lowes.meetingapp.core.dao.beans;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomInfoDO {

    private Long roomId;
    private String roomName;
    private AddressDO addressInfo;
}
