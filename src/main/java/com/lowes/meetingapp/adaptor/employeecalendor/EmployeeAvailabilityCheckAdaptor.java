package com.lowes.meetingapp.adaptor.employeecalendor;

import com.lowes.meetingapp.adaptor.IMeetingAdaptor;
import com.lowes.meetingapp.beans.request.MeetingRoomRequestBean;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.beans.response.ScheduleMeetingResponseBean;
import com.lowes.meetingapp.core.dao.interfaces.IEmployeeCalendorDao;
import com.lowes.meetingapp.core.service.impl.CheckEmployeeCalendorAvaibilityTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component("employeeAvailabilityCheckAdaptor")
public class EmployeeAvailabilityCheckAdaptor implements IMeetingAdaptor<MeetingRoomRequestBean,ResponseBean<ScheduleMeetingResponseBean>>{

    private CheckEmployeeCalendorAvaibilityTask scheduleMeetingTask;

    @Autowired
    private IEmployeeCalendorDao employeeCalendorDao;

    @Autowired
    private ExecutorService executorService;



    @Override
    public void populateResponse(MeetingRoomRequestBean meetingRoomRequestBean,
                                 ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResponseBean)
                                     throws InterruptedException, ExecutionException, TimeoutException {
        Future<ResponseBean<ScheduleMeetingResponseBean>>
                checResponseBeanFuture=executorService.submit(CheckEmployeeCalendorAvaibilityTask.
                builder().employeeCalendorDao(employeeCalendorDao).
                meetingRoomRequestBean(meetingRoomRequestBean).build());

        ResponseBean<ScheduleMeetingResponseBean> avaListReponseBean=checResponseBeanFuture.get(2,TimeUnit.SECONDS);
        scheduleMeetingResponseBean.setResponse(avaListReponseBean.getResponse());
        scheduleMeetingResponseBean.setErrorResponse(avaListReponseBean.getErrorResponse());

    }
}
