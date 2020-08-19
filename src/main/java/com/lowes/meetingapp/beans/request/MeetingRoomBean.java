package com.lowes.meetingapp.beans.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lowes.meetingapp.core.dao.beans.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeetingRoomBean {

    private Long roomId;
    private String roomName;
    private Integer roomCapacity;
    private RoomType roomType;
    private Boolean available;
    private List<Boolean> slots;
    private String lastUpdatedDate;
}
