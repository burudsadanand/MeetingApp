package com.lowes.meetingapp.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeAvailabilityBean {

    private Long employeeId;
    private Boolean isAvailable;
    private String emailId;
}
