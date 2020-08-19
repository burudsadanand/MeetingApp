package com.lowes.meetingapp.core.service.interfaces;

import com.lowes.meetingapp.beans.response.OfficeMeetingRoomInventaryBean;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.core.exception.BusinessServiceException;


public interface IOfficeRoomInventoryService<T> {

    public ResponseBean<T> addRoomInventory(OfficeMeetingRoomInventaryBean officeRoomInventaryBean) throws BusinessServiceException;
}
