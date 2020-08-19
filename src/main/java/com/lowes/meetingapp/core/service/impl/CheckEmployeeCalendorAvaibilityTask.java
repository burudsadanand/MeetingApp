package com.lowes.meetingapp.core.service.impl;

import com.lowes.meetingapp.beans.request.EmployeeBean;
import com.lowes.meetingapp.beans.request.MeetingRoomRequestBean;
import com.lowes.meetingapp.beans.response.*;
import com.lowes.meetingapp.core.dao.beans.EmployeeCalendarDO;
import com.lowes.meetingapp.core.dao.beans.MeetingInfoDO;
import com.lowes.meetingapp.core.dao.interfaces.IEmployeeCalendorDao;
import com.lowes.meetingapp.core.exception.DAOException;
import com.lowes.meetingapp.utils.IdGenerator;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Builder
public class CheckEmployeeCalendorAvaibilityTask implements Callable<ResponseBean<ScheduleMeetingResponseBean>> {


    private IEmployeeCalendorDao employeeCalendorDao;

    private MeetingRoomRequestBean meetingRoomRequestBean;

    @Override
    public ResponseBean<ScheduleMeetingResponseBean> call() throws Exception {
        ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResponseBean=new ResponseBean<>();
        Boolean isEmployeeAvailable=false;
        prepareResponseBean(scheduleMeetingResponseBean);
        isEmployeeAvailable=checkEmployeesAvailability(meetingRoomRequestBean,isEmployeeAvailable,scheduleMeetingResponseBean);
        if(isEmployeeAvailable){
            BaseResponse baseResponse=new BaseResponse();
            baseResponse.setStatus(Status.SUCCESS);
            Meta meta= new Meta();
            meta.setStatus(Status.SUCCESS);
            scheduleMeetingResponseBean.setErrorResponse(baseResponse);
            scheduleMeetingResponseBean.setMeta(meta);
            return scheduleMeetingResponseBean;
        }else{
            BaseResponse baseResponse=new BaseResponse();
            baseResponse.setStatus(Status.FAILURE);
            Meta meta= new Meta();
            meta.setStatus(Status.FAILURE);
            scheduleMeetingResponseBean.setErrorResponse(baseResponse);
            scheduleMeetingResponseBean.setMeta(meta);
            return scheduleMeetingResponseBean;

        }
    }

    private Boolean checkEmployeesAvailability(MeetingRoomRequestBean meetingRoomRequestBean,
                                            Boolean isEmployeeAvailable,
                                            ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResponseBean) throws DAOException {
        List<EmployeeBean> employeeBeanList=meetingRoomRequestBean.getEmployeeBeanList();
        List<String> emailList=new ArrayList<>();
        for(EmployeeBean employeeBean:employeeBeanList){
            emailList.add(employeeBean.getContactBean().getOfficialEmailId());
        }
        List<EmployeeCalendarDO> employeeCalendarDOList=employeeCalendorDao.getEmployeeCalendor(emailList);
        for( EmployeeCalendarDO employeeCalendarDO :employeeCalendarDOList){
           MeetingInfoDO meetingInfoDO= employeeCalendarDO.getMeetingMap().get(meetingRoomRequestBean.getFromDate());
            EmployeeAvailabilityBean employeeAvailabilityBean=new EmployeeAvailabilityBean();
            List<Boolean> slots=meetingInfoDO.getSlots();
            String startTime=meetingRoomRequestBean.getSlot().getFrom();
            String endTime=meetingRoomRequestBean.getSlot().getTo();
            Integer startIndex=IdGenerator.getSlotMap().get(startTime);
            Integer endIndex=IdGenerator.getSlotMap().get(endTime);
            for(int i=startIndex; i<endIndex; i++){
                if(slots.get(startIndex)){
                    isEmployeeAvailable=true;
                    buildEmployeeAvailablityBean(employeeCalendarDO,employeeAvailabilityBean,isEmployeeAvailable);
                    scheduleMeetingResponseBean.getResponse().getEmployeeAvailabilityBeanList().add(employeeAvailabilityBean);
                }else{
                    buildEmployeeAvailablityBean(employeeCalendarDO,employeeAvailabilityBean,isEmployeeAvailable);
                    scheduleMeetingResponseBean.getResponse().getEmployeeAvailabilityBeanList().add(employeeAvailabilityBean);
                }
            }
        }
        return isEmployeeAvailable;

    }

    private void buildEmployeeAvailablityBean(EmployeeCalendarDO employeeCalendarDO,
                                              EmployeeAvailabilityBean employeeAvailabilityBean,
                                              Boolean isAvailable) {
        employeeAvailabilityBean.setEmployeeId(employeeCalendarDO.getEmployeeId());
        employeeAvailabilityBean.setIsAvailable(isAvailable);
        employeeAvailabilityBean.setEmailId(employeeCalendarDO.getEmployeeEmailId());

    }

    private void prepareResponseBean(ResponseBean<ScheduleMeetingResponseBean> employeeAvailabilityResponseBean) {
        ScheduleMeetingResponseBean scheduleMeetingResponseBean=new ScheduleMeetingResponseBean();
        List<EmployeeAvailabilityBean> employeeAvailabilityBeanList=new ArrayList<>();
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(Status.FAILURE);
        scheduleMeetingResponseBean.setEmployeeAvailabilityBeanList(employeeAvailabilityBeanList);
        employeeAvailabilityResponseBean.setResponse(scheduleMeetingResponseBean);
        employeeAvailabilityResponseBean.setErrorResponse(baseResponse);

    }
}
