package com.lowes.meetingapp.core.dao.beans;

import com.lowes.meetingapp.beans.request.EmployeeBean;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

import java.util.List;


@Getter
@Setter
public class MeetingInfoDO {

    private Long meetingId;
    private Long officeFloorNumber;
    private Long officeId;
    private Long roomId;
    private String roomName;
    private String meetingTitle;
    private String meetingDesc;
    private EmployeeBean meetingHost;
    private LocalDate meetingDate;
    private LocalDate meetingTime;
    private List<Boolean> slots;
    private List<EmployeeBean> guests;
    private AddressDO employeeAddress;

}
