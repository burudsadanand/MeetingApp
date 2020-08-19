package com.lowes.meetingapp.core.service.impl;

import com.lowes.meetingapp.beans.request.SearchMeetingRequest;
import com.lowes.meetingapp.beans.response.*;
import com.lowes.meetingapp.core.constants.ErrorEnum;
import com.lowes.meetingapp.core.dao.beans.EmployeeCalendarDO;
import com.lowes.meetingapp.core.dao.beans.MeetingInfoDO;
import com.lowes.meetingapp.core.dao.interfaces.IEmployeeCalendorDao;
import com.lowes.meetingapp.core.exception.BusinessServiceException;
import com.lowes.meetingapp.core.exception.DAOException;
import com.lowes.meetingapp.core.service.interfaces.ISearchScheduledMeetingService;
import com.lowes.meetingapp.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchScheduleMeetingService implements ISearchScheduledMeetingService<SearchMeetingResponse> {

    public static final Logger logger=LoggerFactory.getLogger(SearchScheduleMeetingService.class);
    @Autowired
    private IEmployeeCalendorDao employeeCalendorDao;

    @Override
    public ResponseBean<SearchMeetingResponse> fetchMeetings(SearchMeetingRequest searchMeetingRequests) throws BusinessServiceException {
        ResponseBean<SearchMeetingResponse> searchMeetingResponse=new ResponseBean<>();
        prepareResponseBean(searchMeetingResponse);
            try {
                List<EmployeeCalendarDO> employeeCalendarDOS=employeeCalendorDao.getEmployeeCalendor(searchMeetingRequests.getEmailIds());
                return buildResponse(employeeCalendarDOS,searchMeetingResponse);
            } catch (DAOException e) {
               throw new BusinessServiceException(e,ErrorEnum.INTERSERVER_ERROR);
            }
    }

    private void prepareResponseBean(ResponseBean<SearchMeetingResponse> searchMeetingResponse) {
        Meta meta=new Meta();
        meta.setStatus(Status.FAILURE);
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(Status.FAILURE);
        searchMeetingResponse.setMeta(meta);
    }

    private ResponseBean<SearchMeetingResponse> buildResponse(List<EmployeeCalendarDO> employeeCalendarDOS
            , ResponseBean<SearchMeetingResponse> searchMeetingResponse) {
        SearchMeetingResponse searchMeetingRes=new SearchMeetingResponse();
        Meta meta=new Meta();
        meta.setStatus(Status.SUCCESS);
        searchMeetingResponse.setMeta(meta);
        List<MeetingResponseBean> meetingResponseBeanList=new ArrayList<>();
        Map<String,List<MeetingResponseBean>> meetingResponseBeanMap=new HashMap<>();
        searchMeetingResponse.setResponse(searchMeetingRes);

        for(EmployeeCalendarDO employeeCalendarDO:employeeCalendarDOS){

            employeeCalendarDO.
                    getMeetingMap().
                    entrySet().
                    stream().
                    filter(entry->entry.getValue().isMeetingSchduled()).
                    forEach(entry->{
                MeetingResponseBean meetingResponseBean=new MeetingResponseBean();

                MeetingInfoDO meetingInfoDO=entry.getValue();
                meetingResponseBean.setMeetingId(meetingInfoDO.getMeetingId());
                meetingResponseBean.setOfficeFloorNumber(meetingInfoDO.getOfficeFloorNumber());
              //  meetingResponseBean.setMeetingDate(DateUtils.convertDateToString(meetingInfoDO.getMeetingTime()));
                meetingResponseBean.setMeetingTitle(meetingInfoDO.getMeetingTitle());
                meetingResponseBean.setGuests(meetingInfoDO.getGuests());
                meetingResponseBean.setRoomId(meetingInfoDO.getRoomId());
                meetingResponseBean.setRoomName(meetingInfoDO.getRoomName());
                meetingResponseBean.setTimeSlot(meetingInfoDO.getSlotBean());
                meetingResponseBean.setMeetingHost(meetingInfoDO.getMeetingHost());
                meetingResponseBean.setEmailId(employeeCalendarDO.getEmployeeEmailId());
                meetingResponseBean.setEmployeeId(employeeCalendarDO.getEmployeeId());
                meetingResponseBean.setEmployeeName(employeeCalendarDO.getEmployeeName());

                meetingResponseBeanList.add(meetingResponseBean);
            });
            meetingResponseBeanMap.put(employeeCalendarDO.getEmployeeEmailId(),meetingResponseBeanList);
            searchMeetingRes.setMeetingResponseBeanMap(meetingResponseBeanMap);
        }
        return  searchMeetingResponse;
    }
}
