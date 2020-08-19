package com.lowes.meetingapp.beans.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomInfoBean {

    private Long roomId;
    private String roomName;
    private AddressBean addressInfo;
}

