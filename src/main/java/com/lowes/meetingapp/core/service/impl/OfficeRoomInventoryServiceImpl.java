package com.lowes.meetingapp.core.service.impl;

import com.lowes.meetingapp.beans.request.MeetingRoomBean;
import com.lowes.meetingapp.beans.request.OfficeFloorBean;
import com.lowes.meetingapp.beans.response.*;
import com.lowes.meetingapp.core.constants.ErrorEnum;
import com.lowes.meetingapp.core.dao.beans.AddressDO;
import com.lowes.meetingapp.core.dao.beans.MeetingRoomDO;
import com.lowes.meetingapp.core.dao.beans.OfficeFloorDO;
import com.lowes.meetingapp.core.dao.beans.OfficeRoomInventoryDO;
import com.lowes.meetingapp.core.dao.interfaces.IOfficeRoomInventoryDao;
import com.lowes.meetingapp.core.exception.BusinessServiceException;
import com.lowes.meetingapp.core.exception.DAOException;
import com.lowes.meetingapp.core.service.interfaces.IOfficeRoomInventoryService;
import com.lowes.meetingapp.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeRoomInventoryServiceImpl implements IOfficeRoomInventoryService<RoomInventoryResponse> {
    public static final Logger logger=LoggerFactory.getLogger(OfficeRoomInventoryServiceImpl.class);

    @Autowired
    private IOfficeRoomInventoryDao officeRegisterDao;

    @Override
    public ResponseBean<RoomInventoryResponse> addRoomInventory(OfficeMeetingRoomInventaryBean officeRoomInventaryBean)
            throws BusinessServiceException {
        OfficeRoomInventoryDO officeRoomInventoryDO=new OfficeRoomInventoryDO();
        ResponseBean<RoomInventoryResponse> roomInventoryResponse=new  ResponseBean<>();
        buildBO(officeRoomInventoryDO,officeRoomInventaryBean);

        String officeInteventoryKey= null;
        try {
            officeRegisterDao.addInventory(officeRoomInventoryDO);
            buildResponse(roomInventoryResponse,officeRoomInventoryDO);
        } catch (DAOException e) {
            throw new BusinessServiceException(e,ErrorEnum.ERROR_ADDING_INVENTORY);
        }
       /* if(officeInteventoryKey!=null)
            officeMeetingRoomInventaryBeanMap.put(officeInteventoryKey,officeRoomInventaryBean);*/

        return roomInventoryResponse;
    }

    private void buildResponse(ResponseBean<RoomInventoryResponse> roomInventoryResponse,
                               OfficeRoomInventoryDO officeRoomInventoryDO) {

        RoomInventoryResponse roomInventory=new RoomInventoryResponse();
        roomInventory.setOfficeId(officeRoomInventoryDO.getOfficeId());
        roomInventory.setOfficeName(officeRoomInventoryDO.getOfficeName());
        Meta meta=new Meta();
        meta.setStatus(Status.SUCCESS);
        meta.setResponseTimeInMillis(System.currentTimeMillis());
        roomInventoryResponse.setMeta(meta);
        roomInventoryResponse.setResponse(roomInventory);
    }


    private void  buildBO(OfficeRoomInventoryDO officeRoomInventoryDO,OfficeMeetingRoomInventaryBean officeRoomInventaryBean){
        officeRoomInventoryDO.setOfficeId(officeRoomInventaryBean.getOfficeId());
        officeRoomInventoryDO.setFromDate(DateUtils.convertDateStringToLocalDate(officeRoomInventaryBean.getFromDate()));
        officeRoomInventoryDO.setToDate(DateUtils.convertDateStringToLocalDate(officeRoomInventaryBean.getToDate()));
        AddressDO addressDO=new AddressDO();
        addressDO.setCity(officeRoomInventaryBean.getAddress().getCity());
        addressDO.setCountry(officeRoomInventaryBean.getAddress().getCountry());
        addressDO.setPinCode(officeRoomInventaryBean.getAddress().getPinCode());
        addressDO.setState(officeRoomInventaryBean.getAddress().getState());
        officeRoomInventoryDO.setAddress(addressDO);
        officeRoomInventoryDO.setOfficeName(officeRoomInventaryBean.getOfficeName());
        List<OfficeFloorDO> officeFloorDOS=new ArrayList<>();
        for(OfficeFloorBean floorBean:officeRoomInventaryBean.getOfficeFloors()){
            List<MeetingRoomDO> meetingRoomDOS=new ArrayList<>();
            OfficeFloorDO floorDO=new OfficeFloorDO();
            floorDO.setFloorName(floorBean.getFloorName());
            floorDO.setFloorNumber(floorBean.getFloorNumber());

            for(MeetingRoomBean roomBean:floorBean.getMeetingRooms()){
                MeetingRoomDO meetingRoomDO=new MeetingRoomDO();
                meetingRoomDO.setRoomId(roomBean.getRoomId());
                meetingRoomDO.setAvailable(roomBean.getAvailable());
             //   meetingRoomDO.setLastUpdatedDate(roomBean.getLastUpdatedDate());
                meetingRoomDO.setRoomCapacity(roomBean.getRoomCapacity());
                meetingRoomDO.setRoomType(roomBean.getRoomType());
                List<Boolean> slots=new ArrayList<>();
                for(int i=0; i<95; i++){
                    slots.add(true);
                }
                meetingRoomDO.setSlots(slots);
                meetingRoomDO.setRoomName(roomBean.getRoomName());
                meetingRoomDOS.add(meetingRoomDO);
            }
            floorDO.setMeetingRooms(meetingRoomDOS);
            officeFloorDOS.add(floorDO);
            officeRoomInventoryDO.setOfficeFloors(officeFloorDOS);
            officeRoomInventoryDO.setOfficeName(officeRoomInventaryBean.getOfficeName());
        }
    }
}
