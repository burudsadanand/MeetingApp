package com.lowes.meetingapp.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleMeetingResponseBean extends  BaseResponse {

    private List<EmployeeAvailabilityBean> employeeAvailabilityBeanList;
    private EmployeeCalendarResponseBean employeeCalendarResponseBean;
    private Long officeId;
    private Long meetingId;
    private Long floorNummber;
    private RoomInfoBean roomInfo;
    private MeetingResponseBean meetingResponseBean;
    private LocalDate createDate;
    private Boolean active;
}
