package com.lowes.meetingapp.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeCalendarResponseBean extends BaseResponse{

    private String employeeName;
    private String employeeId;
    private AddressBean employeeAddress;
    private Set<MeetingResponseBean> meetings;
}
