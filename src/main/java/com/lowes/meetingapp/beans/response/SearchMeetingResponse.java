package com.lowes.meetingapp.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lowes.meetingapp.core.dao.beans.MeetingScheduleDO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchMeetingResponse {


    private Map<String,List<MeetingResponseBean>> meetingResponseBeanMap;


}
