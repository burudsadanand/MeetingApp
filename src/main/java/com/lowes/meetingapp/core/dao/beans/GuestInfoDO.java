package com.lowes.meetingapp.core.dao.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestInfoDO {

    private String emailId;
    private String name;
    private AddressDO addressInfo;

}
