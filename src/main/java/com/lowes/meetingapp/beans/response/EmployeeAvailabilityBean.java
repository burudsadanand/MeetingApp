package com.lowes.meetingapp.beans.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeAvailabilityBean {

    private Long employeeId;
    private Boolean isAvailable;
    private String emailId;
}
