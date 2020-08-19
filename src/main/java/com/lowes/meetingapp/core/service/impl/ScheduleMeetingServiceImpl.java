package com.lowes.meetingapp.core.service.impl;

import com.google.gson.Gson;
import com.lowes.meetingapp.adaptor.IMeetingAdaptor;
import com.lowes.meetingapp.beans.request.MeetingRoomRequestBean;
import com.lowes.meetingapp.beans.response.MeetingResponseBean;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.beans.response.ScheduleMeetingResponseBean;
import com.lowes.meetingapp.beans.response.Status;
import com.lowes.meetingapp.core.constants.ErrorEnum;
import com.lowes.meetingapp.core.exception.BusinessServiceException;
import com.lowes.meetingapp.core.service.interfaces.IScheduleMeetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service
public class ScheduleMeetingServiceImpl implements IScheduleMeetingService<MeetingResponseBean,MeetingRoomRequestBean> {

    private static final Logger logger=LoggerFactory.getLogger(ScheduleMeetingServiceImpl.class);

    @Inject
    private LinkedHashMap<String,IMeetingAdaptor> scheduleMeetingAdaptorMap;


    @Override
    public ResponseBean<MeetingResponseBean> schedule(MeetingRoomRequestBean meetingRoomRequestBean)
            throws BusinessServiceException {
        ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResponseBean=new ResponseBean<>();
        for(IMeetingAdaptor scheduleMeetingAdaptor:getListOfAdapters()){
            String roomAndMeetingDate=meetingRoomRequestBean.getOfficeId()+"#"+meetingRoomRequestBean.getFromDate();
            synchronized (roomAndMeetingDate){
                try {
                    scheduleMeetingAdaptor.populateResponse(meetingRoomRequestBean,scheduleMeetingResponseBean);
                    if(scheduleMeetingResponseBean.getErrorResponse()!=null && scheduleMeetingResponseBean.getErrorResponse().getStatus().equals(Status.FAILURE) ){
                        ResponseBean<MeetingResponseBean> meetingResponse=new ResponseBean<>();
                        meetingResponse.setResponse(scheduleMeetingResponseBean.getResponse().getMeetingResponseBean());
                        meetingResponse.setMeta(scheduleMeetingResponseBean.getMeta());
                        return meetingResponse;
                    }
                } catch (InterruptedException e) {
                   throw new BusinessServiceException(e,ErrorEnum.INTERSERVER_ERROR);
                } catch (ExecutionException e) {
                    throw new BusinessServiceException(e,ErrorEnum.INTERSERVER_ERROR);
                } catch (TimeoutException e) {
                    throw new BusinessServiceException(e,ErrorEnum.INTERSERVER_ERROR);
                }
            }

        }
        ResponseBean<MeetingResponseBean> meetingResponse=new ResponseBean<>();
        meetingResponse.setResponse(scheduleMeetingResponseBean.getResponse().getMeetingResponseBean());
        meetingResponse.setMeta(scheduleMeetingResponseBean.getMeta());
        Gson gson=new Gson();
       // logger.info(gson.toJson(meetingResponse));
        return meetingResponse;
    }


    private LinkedList<IMeetingAdaptor> getListOfAdapters(){
        LinkedList<IMeetingAdaptor> adaptorList=new LinkedList<>();
        scheduleMeetingAdaptorMap.entrySet().forEach(entry->{
            adaptorList.add(entry.getValue());

        });
        return  adaptorList;
    }
}
