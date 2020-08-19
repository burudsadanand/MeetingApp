package com.lowes.meetingapp.beans.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactBean {
    private Long mobileNumber1;
    private Long mobileNumber2;
    private Long officeNumber;
    private Long isdCode;
    private String personalEmailId;
    private String officialEmailId;
}
