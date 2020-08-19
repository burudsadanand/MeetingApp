package com.lowes.meetingapp.beans.response;

import com.lowes.meetingapp.beans.request.EmployeeBean;
import com.lowes.meetingapp.beans.request.SlotsBean;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class MeetingResponseBean {

    private Long meetingId;
    private Long officeFloorNumber;
    private AddressBean officeAddress;
    private Long officeId;
    private Long roomId;
    private String roomName;
    private String meetingTitle;
    private String meetingDesc;
    private EmployeeBean meetingHost;
    private String meetingDate;
    private SlotsBean timeSlot;
    private List<EmployeeBean> guests;
}
