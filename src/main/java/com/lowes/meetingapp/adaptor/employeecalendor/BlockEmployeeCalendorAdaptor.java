package com.lowes.meetingapp.adaptor.employeecalendor;

import com.lowes.meetingapp.adaptor.IMeetingAdaptor;
import com.lowes.meetingapp.beans.request.MeetingRoomRequestBean;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.beans.response.ScheduleMeetingResponseBean;
import com.lowes.meetingapp.core.dao.interfaces.IEmployeeCalendorDao;
import com.lowes.meetingapp.core.service.impl.BlockEmployeeCalendorTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;


@Component("blockEmployeeCalendorAdaopter")
public class BlockEmployeeCalendorAdaptor implements
        IMeetingAdaptor<MeetingRoomRequestBean,ResponseBean<ScheduleMeetingResponseBean>> {

    @Autowired
    private IEmployeeCalendorDao employeeCalendorDao;

    @Autowired
    private ExecutorService executorService;


    @Override
    public void populateResponse(MeetingRoomRequestBean meetingRoomRequestBean,
                                 ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResponseBean)
                                                throws InterruptedException, ExecutionException, TimeoutException {

        Future<ResponseBean<ScheduleMeetingResponseBean>> employeeBlockResponseBean=
                executorService.submit(BlockEmployeeCalendorTask.builder().
                        employeeCalendorDao(employeeCalendorDao).
                        scheduleMeetingResponseBean(scheduleMeetingResponseBean).
                meetingRoomRequestBean(meetingRoomRequestBean).build());

        ResponseBean<ScheduleMeetingResponseBean> resonseBean=employeeBlockResponseBean.get(2,TimeUnit.MINUTES);
        scheduleMeetingResponseBean.setResponse(resonseBean.getResponse());
    }
}
