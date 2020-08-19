package com.lowes.meetingapp.core.service.interfaces;

import com.lowes.meetingapp.beans.request.SearchMeetingRequest;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.core.exception.BusinessServiceException;

public interface ISearchScheduledMeetingService<T> {

    public ResponseBean<T> fetchMeetings(SearchMeetingRequest searchMeetingRequests) throws BusinessServiceException;

}
