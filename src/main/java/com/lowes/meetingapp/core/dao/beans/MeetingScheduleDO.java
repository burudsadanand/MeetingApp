package com.lowes.meetingapp.core.dao.beans;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDateTime;

@Getter
@Setter
public class MeetingScheduleDO {

 //  private Long meetingId;
   private RoomInfoDO roomInfo;
   private MeetingInfoDO meetingInfo;
   private LocalDateTime createDate;
   private Boolean active;

}
