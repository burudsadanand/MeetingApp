package com.lowes.meetingapp.core.dao.impl;

import com.lowes.meetingapp.core.dao.beans.MeetingScheduleDO;
import com.lowes.meetingapp.core.dao.interfaces.IScheduleMeetingDao;
import com.lowes.meetingapp.core.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;


@Component
public class ScheduleMeetingDao implements IScheduleMeetingDao<MeetingScheduleDO,MeetingScheduleDO> {


    @Inject
    private Map<Long, MeetingScheduleDO> scheduledMeetingMap;


    @Override
    public MeetingScheduleDO schedule(MeetingScheduleDO meetingScheduleDO) throws DAOException {
        scheduledMeetingMap.put(meetingScheduleDO.getMeetingInfo().getMeetingId(),meetingScheduleDO);
        return meetingScheduleDO;
    }
}
