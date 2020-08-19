package com.lowes.meetingapp.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDetailsBean {
    private Long mobileNumber1;
    private Long mobileNumber2;
    private Long officeNumber;
    private Long isdCode;
    private String personalEmailId;
    private String officialEmailId;
}
