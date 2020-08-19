package com.lowes.meetingapp.core.dao.beans;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

import java.util.Map;

@Getter
@Setter
public class EmployeeCalendarDO {

    private String employeeName;
    private String employeeEmailId;
    private Long employeeId;
    private AddressDO employeeAddress;
    private Map<String,MeetingInfoDO> meetingMap;

}
