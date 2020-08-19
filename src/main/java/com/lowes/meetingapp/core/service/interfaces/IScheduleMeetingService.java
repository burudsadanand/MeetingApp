package com.lowes.meetingapp.core.service.interfaces;

import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.core.exception.BusinessServiceException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface IScheduleMeetingService<T,R> {

    public ResponseBean<T> schedule(R r ) throws BusinessServiceException;
}
