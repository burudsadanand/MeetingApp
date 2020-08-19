package com.lowes.meetingapp.core.dao.beans;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.List;

@Getter
@Setter
public class MeetingRoomDO  {

    private Long roomId;
    private String roomName;
    private Integer roomCapacity;
    private RoomType roomType;
    private Boolean available;
    private List<Boolean> slots;
    private LocalDate lastUpdatedDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate creationDate;
}
