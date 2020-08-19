package com.lowes.meetingapp.adaptor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface IMeetingAdaptor<R,Q> {

    public void populateResponse(R r,Q q) throws InterruptedException, ExecutionException, TimeoutException;
}
