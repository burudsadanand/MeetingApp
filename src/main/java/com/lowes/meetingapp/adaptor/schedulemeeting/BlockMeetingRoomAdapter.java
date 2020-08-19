package com.lowes.meetingapp.adaptor.schedulemeeting;

import com.lowes.meetingapp.adaptor.IMeetingAdaptor;
import com.lowes.meetingapp.beans.request.MeetingRoomRequestBean;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.beans.response.ScheduleMeetingResponseBean;
import com.lowes.meetingapp.core.constants.ErrorEnum;
import com.lowes.meetingapp.core.dao.impl.OfficeRoomInventoryDao;
import com.lowes.meetingapp.core.dao.interfaces.IScheduleMeetingDao;
import com.lowes.meetingapp.core.exception.BusinessServiceException;
import com.lowes.meetingapp.core.service.impl.BlockMeetingRoomTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component("blockMeetingRoomAdapter")
public class BlockMeetingRoomAdapter implements IMeetingAdaptor<MeetingRoomRequestBean,ResponseBean<ScheduleMeetingResponseBean>> {


    @Autowired
    private IScheduleMeetingDao scheduleMeetingDao;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private OfficeRoomInventoryDao officeRoomInventoryDao;



    @Override
    public void populateResponse(MeetingRoomRequestBean meetingRoomRequestBean,
                                 ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResponseBean) throws BusinessServiceException  {
        Future<ResponseBean<ScheduleMeetingResponseBean>> meetingScheduleResponse=
                executorService.
                        submit(BlockMeetingRoomTask.builder().
                                meetingRoomRequestBean(meetingRoomRequestBean).
                                scheduleMeetingResponseBean(scheduleMeetingResponseBean).
                                scheduleMeetingDao(scheduleMeetingDao).
                                officeRoomInventoryDao(officeRoomInventoryDao).
                                build());
        try {
            ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResponse=meetingScheduleResponse.get(10,TimeUnit.SECONDS);
            scheduleMeetingResponseBean.setResponse(scheduleMeetingResponse.getResponse());
        } catch (InterruptedException e) {
            throw new BusinessServiceException(e,ErrorEnum.INTERSERVER_ERROR);
        } catch (ExecutionException e) {
            throw new BusinessServiceException(e,ErrorEnum.INTERSERVER_ERROR);
        } catch (TimeoutException e) {
            throw new BusinessServiceException(e,ErrorEnum.INTERSERVER_ERROR);
        }
    }
}
