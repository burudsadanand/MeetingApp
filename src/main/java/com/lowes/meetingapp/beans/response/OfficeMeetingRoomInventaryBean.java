package com.lowes.meetingapp.beans.response;

import com.lowes.meetingapp.beans.request.OfficeFloorBean;
import com.lowes.meetingapp.core.dao.beans.AddressDO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OfficeMeetingRoomInventaryBean extends  BaseResponse {

    private Long officeId;
    private String officeName;
    private List<OfficeFloorBean> officeFloors;
    private AddressDO address;
    private String createDate;
    private String lastUpdatedDate;
    private String fromDate;
    private String toDate;





    /*public static void main(String[] args){
        Gson gson=new Gson();
        String jsonResponse="";
        OfficeMeetingRoomInventaryBean officeMeetingRoomInventaryBean=new OfficeMeetingRoomInventaryBean();
        officeMeetingRoomInventaryBean.setOfficeId(12345675l);
        AddressDO addressDO=new AddressDO();
        addressDO.setState("Karnataka");
        addressDO.setPinCode("560078");
        addressDO.setCountry("India");
        addressDO.setCity("Bangalore");

        officeMeetingRoomInventaryBean.setAddress(addressDO);
        officeMeetingRoomInventaryBean.setCreateDate(new LocalDate());
        officeMeetingRoomInventaryBean.setLastUpdatedDate(new LocalDate());
        List<OfficeFloorBean> floors=new ArrayList<>();
        OfficeFloorBean officeFloorBean=new OfficeFloorBean();
        officeFloorBean.setFloorName("1FloorMeetingRoom");
        officeFloorBean.setFloorNumber(101);
        List<MeetingRoomBean> meetingRoomBeanList=new ArrayList<>();
        MeetingRoomBean meetingRoomBean=new MeetingRoomBean();
        meetingRoomBean.setAvailable(true);
        meetingRoomBean.setLastUpdatedDate(new LocalDate());
        meetingRoomBean.setRoomCapacity(20);
        meetingRoomBean.setRoomId(701346l);
        meetingRoomBean.setRoomType(RoomType.CONFERENCE);
        List<Boolean> slots=new ArrayList<>();
        List<Boolean> slots2=new ArrayList<>();
        for(int i=0; i<95; i++){
            slots.add(true);
        }
        meetingRoomBean.setSlots(slots);
        meetingRoomBeanList.add(meetingRoomBean);
        MeetingRoomBean meetingRoomBean2=new MeetingRoomBean();
        meetingRoomBean2.setAvailable(true);
        meetingRoomBean2.setLastUpdatedDate(new LocalDate().toDateTime);
        meetingRoomBean2.setRoomCapacity(10);
        meetingRoomBean2.setRoomId(701345l);
        meetingRoomBean2.setRoomType(RoomType.MEETING);
        for(int i=0; i<95; i++){
            slots2.add(true);
        }
        meetingRoomBean2.setSlots(slots2);
        meetingRoomBeanList.add(meetingRoomBean2);
        officeFloorBean.setMeetingRooms(meetingRoomBeanList);
        floors.add(officeFloorBean);
        officeMeetingRoomInventaryBean.setOfficeFloors(floors);
        jsonResponse=gson.toJson(officeMeetingRoomInventaryBean);
        System.out.println(jsonResponse);
    }*/
}
