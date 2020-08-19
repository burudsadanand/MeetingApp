package com.lowes.meetingapp.core.service.impl;

import com.lowes.meetingapp.beans.request.ContactBean;
import com.lowes.meetingapp.beans.request.EmployeeBean;
import com.lowes.meetingapp.beans.request.MeetingRoomRequestBean;
import com.lowes.meetingapp.beans.request.SlotsBean;
import com.lowes.meetingapp.beans.response.*;
import com.lowes.meetingapp.core.constants.ErrorEnum;
import com.lowes.meetingapp.core.dao.beans.MeetingRoomDO;
import com.lowes.meetingapp.core.dao.beans.OfficeFloorDO;
import com.lowes.meetingapp.core.dao.beans.OfficeRoomInventoryDO;
import com.lowes.meetingapp.core.dao.impl.OfficeRoomInventoryDao;
import com.lowes.meetingapp.core.dao.interfaces.IScheduleMeetingDao;
import com.lowes.meetingapp.core.exception.DAOException;
import com.lowes.meetingapp.utils.DateUtils;
import com.lowes.meetingapp.utils.IdGenerator;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@Builder
public class BlockMeetingRoomTask implements Callable<ResponseBean<ScheduleMeetingResponseBean>> {
    public static final Logger logger=LoggerFactory.getLogger(BlockMeetingRoomTask.class);

    private MeetingRoomRequestBean meetingRoomRequestBean;
    private IScheduleMeetingDao scheduleMeetingDao;
    private ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResponseBean;
    private OfficeRoomInventoryDao officeRoomInventoryDao;

    @Override
    public ResponseBean<ScheduleMeetingResponseBean> call() throws Exception {

     prepareResponseBean(scheduleMeetingResponseBean);
     OfficeRoomInventoryDO officeRoomInventoryDO=new OfficeRoomInventoryDO();
     checkRoomAvailabilityAndBookTheRoom(officeRoomInventoryDO, scheduleMeetingResponseBean);
     return scheduleMeetingResponseBean;
    }

    private void checkRoomAvailabilityAndBookTheRoom(OfficeRoomInventoryDO officeRoomInventoryDO,
                                                     ResponseBean<ScheduleMeetingResponseBean> responseBean) throws DAOException {
        String roomInventoryId=getRoomFromOfficeInventoryMap(officeRoomInventoryDO);
        officeRoomInventoryDO=officeRoomInventoryDao.fetchOfficeInventory(roomInventoryId);
        Optional<OfficeRoomInventoryDO> optionalRoomInventoryDO=Optional.of(officeRoomInventoryDO);

        if(!optionalRoomInventoryDO.isPresent()){
            throw new DAOException(ErrorEnum.ENTITY_NOTEXISTS.getErrorMessage(),ErrorEnum.ENTITY_NOTEXISTS);
        }

        List<OfficeFloorDO> officeFloorDOS=officeRoomInventoryDO.getOfficeFloors();
        Boolean slotAvailable=false;

        for(OfficeFloorDO officeFloorDO:officeFloorDOS){
            if(officeFloorDO.getFloorNumber().equals(meetingRoomRequestBean.getFloorNumber())){
                List<MeetingRoomDO> meetingRoomDOS=officeFloorDO.getMeetingRooms();
                for(MeetingRoomDO meetingRoomDO:meetingRoomDOS){
                    if(meetingRoomDO.getRoomId().equals(meetingRoomRequestBean.getRoomId())){
                        List<Boolean> slots=meetingRoomDO.getSlots();
                        String startTime=meetingRoomRequestBean.getSlot().getFrom();
                        String endTime=meetingRoomRequestBean.getSlot().getTo();
                        Integer startIndex=IdGenerator.getSlotMap().get(startTime);
                        Integer endIndex=IdGenerator.getSlotMap().get(endTime);
                        for(int i=startIndex; i<endIndex; i++){
                            if(slots.get(i)){
                                slotAvailable=true;
                                slots.add(i,false);
                            }else{
                                slotAvailable=false;
                                slots.add(i,false);
                            }
                            meetingRoomDO.setSlots(slots);
                        }
                        if(slotAvailable){
                            Long meetingId=IdGenerator.generateMeetingId();
                            blockMeetingRoom(officeRoomInventoryDO,roomInventoryId);
                            updateMeetingScheduleDB(meetingRoomRequestBean,meetingRoomDO,meetingId);
                            buildScheduleMeetingResponse(scheduleMeetingResponseBean,
                                                         meetingRoomRequestBean,
                                                         officeRoomInventoryDO.getOfficeId(),
                                                         officeFloorDO.getFloorNumber(),
                                                          meetingRoomDO,meetingId);
                            responseBean.getErrorResponse().setStatus(Status.SUCCESS);
                        }
                    }
                }
            }
        }
    }
    private String  getRoomFromOfficeInventoryMap(OfficeRoomInventoryDO officeRoomInventoryDO) throws DAOException {
        String inventoryId=meetingRoomRequestBean.getOfficeId()+"#"+meetingRoomRequestBean.getFromDate();
        return inventoryId;
    }


    private  void prepareResponseBean(ResponseBean<ScheduleMeetingResponseBean> responseBean){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(Status.FAILURE);
        responseBean.setErrorResponse(baseResponse);
    }

    private void blockMeetingRoom(OfficeRoomInventoryDO officeRoomInventoryDO,
                                String roomInventoryId){
        officeRoomInventoryDao.blockRoom(roomInventoryId,officeRoomInventoryDO);
    }

    private void updateMeetingScheduleDB(MeetingRoomRequestBean meetingRoomRequestBean,
                                         MeetingRoomDO meetingRoomDO,Long meetingId){

    }

    private void buildScheduleMeetingResponse(ResponseBean<ScheduleMeetingResponseBean> scheduleMeetingResBean,
                                              MeetingRoomRequestBean meetingRoomRequestBean,
                                      Long officeId,
                                      Long floorNo,
                                      MeetingRoomDO meetingRoomDO,Long meetingId) {

        ScheduleMeetingResponseBean scheduleMeetingResponseBean=scheduleMeetingResBean.getResponse();
        if(scheduleMeetingResponseBean==null)
            scheduleMeetingResponseBean=new ScheduleMeetingResponseBean();
        scheduleMeetingResponseBean.setActive(true);
        scheduleMeetingResponseBean.setMeetingId(meetingId);
        scheduleMeetingResponseBean.setOfficeId(officeId);
        scheduleMeetingResponseBean.setFloorNummber(floorNo);
        scheduleMeetingResponseBean.setCreateDate(DateUtils.convertDateStringToLocalDate(meetingRoomRequestBean.getCreationDate()));
        RoomInfoBean roomInfoDO = new RoomInfoBean();
        scheduleMeetingResponseBean.setRoomInfo(roomInfoDO);
        roomInfoDO.setRoomId(meetingRoomDO.getRoomId());
        roomInfoDO.setRoomName(meetingRoomDO.getRoomName());
        AddressBean addressDO = new AddressBean();
        roomInfoDO.setAddressInfo(addressDO);
        addressDO.setState(roomInfoDO.getAddressInfo().getState());
        addressDO.setPinCode(roomInfoDO.getAddressInfo().getPinCode());
        addressDO.setCountry(roomInfoDO.getAddressInfo().getCountry());
        addressDO.setCity(roomInfoDO.getAddressInfo().getCity());


        MeetingResponseBean meetingInfoDO = new MeetingResponseBean();
        AddressBean officeAddres=new AddressBean();
        officeAddres.setCity(meetingRoomRequestBean.getAddressBean().getCity());
        officeAddres.setState(meetingRoomRequestBean.getAddressBean().getState());
        officeAddres.setCountry(meetingRoomRequestBean.getAddressBean().getCountry());
        officeAddres.setPinCode(meetingRoomRequestBean.getAddressBean().getPinCode());
        meetingInfoDO.setOfficeAddress(officeAddres);
        meetingInfoDO.setMeetingId(meetingId);
        meetingInfoDO.setOfficeFloorNumber(floorNo);
        EmployeeBean employeeBean=new EmployeeBean();
        meetingInfoDO.setMeetingHost(employeeBean);
        employeeBean.setLastName(meetingRoomRequestBean.getOrganizer().getLastName());
        employeeBean.setFirstName(meetingRoomRequestBean.getOrganizer().getFirstName());
        ContactBean contactBean1=new ContactBean();
        employeeBean.setContactBean(contactBean1);
        contactBean1.setMobileNumber1(meetingRoomRequestBean.getOrganizer().getContactBean().getMobileNumber1());
        contactBean1.setMobileNumber2(meetingRoomRequestBean.getOrganizer().getContactBean().getMobileNumber2());
        contactBean1.setOfficialEmailId(meetingRoomRequestBean.getOrganizer().getContactBean().getOfficialEmailId());
        scheduleMeetingResponseBean.setMeetingResponseBean(meetingInfoDO);
        meetingInfoDO.setOfficeId(meetingRoomRequestBean.getOfficeId());
        meetingInfoDO.setRoomId(meetingRoomDO.getRoomId());
        meetingInfoDO.setTimeSlot(meetingInfoDO.getTimeSlot());
        meetingInfoDO.setRoomName(meetingRoomDO.getRoomName());
        meetingInfoDO.setMeetingDesc(meetingRoomRequestBean.getDescription());
        meetingInfoDO.setMeetingTitle(meetingRoomRequestBean.getMeetingTitle());
        meetingInfoDO.setMeetingDate(meetingRoomRequestBean.getFromDate());
        meetingInfoDO.setTimeSlot(new SlotsBean(meetingRoomRequestBean.getSlot().getFrom(),meetingRoomRequestBean.getSlot().getTo()));
        List<EmployeeBean> guestBeans = new ArrayList<>();
        meetingInfoDO.setGuests(guestBeans);
        meetingRoomRequestBean.getEmployeeBeanList().stream().forEach(guest -> {
            ContactBean contactBean = new ContactBean();
            EmployeeBean guestBean = new EmployeeBean();
            guestBean.setFirstName(guest.getFirstName());
            guestBean.setLastName(guest.getLastName());
            guestBean.setEmployeeId(guest.getEmployeeId());
            guestBean.setContactBean(contactBean);
            guestBeans.add(guestBean);
            contactBean.setMobileNumber1(guest.getContactBean().getMobileNumber1());
            contactBean.setMobileNumber2(guest.getContactBean().getMobileNumber2());
            contactBean.setIsdCode(guest.getContactBean().getIsdCode());
            contactBean.setOfficialEmailId(guest.getContactBean().getOfficialEmailId());
            contactBean.setPersonalEmailId(guest.getContactBean().getOfficialEmailId());
            });

        scheduleMeetingResBean.setResponse(scheduleMeetingResponseBean);
        //scheduleMeetingBeanMap.put(System.currentTimeMillis(),scheduleMeetingResBean);
    }


    }

