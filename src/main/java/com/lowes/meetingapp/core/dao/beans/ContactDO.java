package com.lowes.meetingapp.core.dao.beans;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDO {

    private Long mobileNumber1;
    private Long mobileNumber2;
    private Long officeNumber;
    private Long isdCode;
    private String personalEmailId;
    private String officialEmailId;
}
