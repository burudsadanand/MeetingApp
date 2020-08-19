package com.lowes.meetingapp.core.service.impl;

import com.lowes.meetingapp.beans.request.MeetingRoomRequestBean;
import com.lowes.meetingapp.beans.request.SlotsBean;
import com.lowes.meetingapp.beans.response.*;
import com.lowes.meetingapp.core.dao.beans.AddressDO;
import com.lowes.meetingapp.core.dao.beans.EmployeeCalendarDO;
import com.lowes.meetingapp.core.dao.beans.MeetingInfoDO;
import com.lowes.meetingapp.core.dao.interfaces.IEmployeeCalendorDao;
import com.lowes.meetingapp.core.exception.DAOException;
import com.lowes.meetingapp.utils.DateUtils;
import com.lowes.meetingapp.utils.IdGenerator;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Builder
public class BlockEmployeeCalendorTask implements Callable<ResponseBean<ScheduleMeetingResponseBean>> {

    public static final Logger logger=LoggerFactory.getLogger(BlockEmployeeCalendorTask.class);
    private IEmployeeCalendorDao employeeCalendorDao;

    private MeetingRoomRequestBean meetingRoomRequestBean;

    private ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResponseBean;

    @Override
    public ResponseBean<ScheduleMeetingResponseBean> call() throws Exception {
        prepareResponseBean(scheduleMeetingResponseBean);
        blockEmployeeCalendor(meetingRoomRequestBean,scheduleMeetingResponseBean.getResponse());
        return scheduleMeetingResponseBean;
    }

    private void blockEmployeeCalendor(MeetingRoomRequestBean meetingRoomRequestBean,
                                       ScheduleMeetingResponseBean response) throws DAOException {
            for(EmployeeAvailabilityBean employeeAvailabilityBean :response.getEmployeeAvailabilityBeanList()){
            EmployeeCalendarDO employeeCalendarDO=employeeCalendorDao.fetchEmployeCalendor(employeeAvailabilityBean.getEmailId());
            if(employeeCalendarDO!=null){
                Map<String,MeetingInfoDO> infoDOMap=employeeCalendarDO.getMeetingMap();
                MeetingInfoDO meetingInfoDO=infoDOMap.get(meetingRoomRequestBean.getFromDate());
                infoDOMap.put(meetingRoomRequestBean.getFromDate(),meetingInfoDO);
                List<Boolean> slots=new ArrayList<>();
                for(int i=0; i<95; i++){
                    slots.add(true);
                }
                String startTime=meetingRoomRequestBean.getSlot().getFrom();
                String endTime=meetingRoomRequestBean.getSlot().getTo();
                Integer startIndex=IdGenerator.getSlotMap().get(startTime);
                Integer endIndex=IdGenerator.getSlotMap().get(endTime);
                for(int i=startIndex; i<endIndex; i++){
                    if(meetingInfoDO.getSlots().get(i)){
                        slots.add(i,false);
                    }else{
                        slots.add(i,true);
                    }
                }
                meetingInfoDO.setSlots(slots);
                Map<String,MeetingInfoDO> meetingInfoDOMap=employeeCalendarDO.getMeetingMap();
                meetingInfoDOMap.put(meetingRoomRequestBean.getFromDate(),meetingInfoDO);
                employeeCalendarDO.setMeetingMap(meetingInfoDOMap);
                buildBlockCalendaorDO(employeeAvailabilityBean.getEmailId(),meetingRoomRequestBean,employeeCalendarDO,meetingInfoDO,response);
            }else{
                employeeCalendarDO=new EmployeeCalendarDO();
                buildBlockCalendaorDO(employeeAvailabilityBean.getEmailId(),meetingRoomRequestBean,employeeCalendarDO,new MeetingInfoDO(),response);
                employeeCalendorDao.createEmployeeCalendor(employeeCalendarDO);
            }


        }

    }

    private void buildBlockCalendaorDO(String emailId,MeetingRoomRequestBean meetingRoomRequestBean,
                                       EmployeeCalendarDO employeeCalendarDO,MeetingInfoDO meetingInfoDO,
                                       ScheduleMeetingResponseBean responseBean) {
        AddressDO addressDO=new AddressDO();
        addressDO.setCity(meetingRoomRequestBean.getAddressBean().getCity());
        addressDO.setCountry(meetingRoomRequestBean.getAddressBean().getCountry());
        addressDO.setPinCode(meetingRoomRequestBean.getAddressBean().getPinCode());
        addressDO.setState(meetingRoomRequestBean.getAddressBean().getState());
        employeeCalendarDO.setEmployeeAddress(addressDO);
        meetingInfoDO.setMeetingId(responseBean.getMeetingId());
        meetingInfoDO.setOfficeId(responseBean.getOfficeId());
        meetingInfoDO.setMeetingTitle(meetingRoomRequestBean.getMeetingTitle());
        meetingInfoDO.setMeetingDesc(meetingRoomRequestBean.getDescription());
        meetingInfoDO.setRoomName(meetingRoomRequestBean.getRoomName());
        meetingInfoDO.setRoomId(meetingRoomRequestBean.getRoomId());
        meetingInfoDO.setGuests(meetingRoomRequestBean.getEmployeeBeanList());
        meetingInfoDO.setMeetingHost(meetingRoomRequestBean.getOrganizer());
        meetingInfoDO.setMeetingDate(DateUtils.convertDateStringToLocalDate(meetingRoomRequestBean.getFromDate()));
        meetingInfoDO.setMeetingTime(DateUtils.convertDateStringToLocalDate(meetingRoomRequestBean.getFromTime()));
        meetingInfoDO.setOfficeFloorNumber(meetingRoomRequestBean.getFloorNumber());
       // meetingInfoDO.setSlots(meetingInfo.getSlots());
        meetingInfoDO.setSlotBean(new SlotsBean(meetingRoomRequestBean.getFromTime(),meetingRoomRequestBean.getToTime()));
        meetingInfoDO.setMeetingSchduled(true);
        Map<String, MeetingInfoDO> employeCalMap=employeeCalendarDO.getMeetingMap();
        employeCalMap.put(meetingRoomRequestBean.getFromDate(),meetingInfoDO);
        employeeCalendarDO.setMeetingMap(employeCalMap);
        employeeCalendorDao.updateEmployeeCalendor(emailId,employeeCalendarDO);

    }


    private void prepareResponseBean(ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResponseBean) {
        EmployeeCalendarResponseBean employeeCalendarResponseBean=new EmployeeCalendarResponseBean();
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(Status.FAILURE);
        ScheduleMeetingResponseBean scheduleMeetingResponseBean1=scheduleMeetingResponseBean.getResponse();
        scheduleMeetingResponseBean1.setEmployeeCalendarResponseBean(employeeCalendarResponseBean);
        scheduleMeetingResponseBean.setResponse(scheduleMeetingResponseBean1);
        scheduleMeetingResponseBean.setErrorResponse(baseResponse);

    }
}
