package com.lowes.meetingapp.beans.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class EmployeeCalendarResponseBean extends BaseResponse{

    private String employeeName;
    private String employeeId;
    private AddressBean employeeAddress;
    private Set<MeetingResponseBean> meetings;
}
