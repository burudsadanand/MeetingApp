package com.lowes.meetingapp.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lowes.meetingapp.core.dao.beans.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MeetingRoomRequestBean {

    private Long roomId;
    private Long meetingId;
    private Long officeId;
    private String officeName;
    private String roomName;
    private Integer roomCapacity;
    private Long floorNumber;
    private RoomType roomType;
    private Boolean available;
    private String fromDate;
    private String toDate;
    private String fromTime;
    private String toTime;
    private SlotsBean slot;
    private String lastUpdatedDate;
    @JsonProperty("addressLocation")
    private AddressBean addressBean;
    @JsonProperty("attendees")
    private List<EmployeeBean> employeeBeanList;
    @JsonProperty("emailIds")
    private List<String> guestEmailIds;
    private String creationDate;
    private String meetingTitle;
    private String subject;
    private String description;
    @JsonProperty("organizer")
    private EmployeeBean organizer;

   /* public static void adasdasd(String[] rgs){
        MeetingRoomRequestBean meetingRoomBean=new MeetingRoomRequestBean();
        meetingRoomBean.setRoomId(701345l);
        meetingRoomBean.setRoomName("WarRoom");
        meetingRoomBean.setMeetingTitle("Interview|Lead Engineer| Program Assignmenet");
        meetingRoomBean.setOfficeId(12345675l);
        AddressBean addressBean=new AddressBean();
        addressBean.setCity("Bangalore");
        addressBean.setCountry("India");
        addressBean.setPinCode("560078");
        addressBean.setState("Karnataka");
        meetingRoomBean.setAddressBean(addressBean);
        meetingRoomBean.setAvailable(true);
        meetingRoomBean.setDescription(" Final Roudnd of Interview with Sadanand");
        meetingRoomBean.setFloorNumber(101l);
        meetingRoomBean.setRoomCapacity(20);
        meetingRoomBean.setRoomType(RoomType.CONFERENCE);
        meetingRoomBean.setFromDate("2020-08-20");
        meetingRoomBean.setToDate("2020-08-20");
        meetingRoomBean.setFromTime("2020-08-20");
        meetingRoomBean.setToTime("2020-08-20");
        meetingRoomBean.setSlot(new SlotsBean("11:00","12:00"));
        List<EmployeeBean> employeeBeans=new ArrayList<>();
        EmployeeBean employeeBean=new EmployeeBean();
        employeeBean.setFirstName("Bhavesh");
        employeeBean.setLastName("S");
        ContactBean contactBean=new ContactBean();
        contactBean.setIsdCode(91l);
        contactBean.setMobileNumber1(9186453212l);
        contactBean.setOfficeNumber(8084232332212l);
        contactBean.setOfficialEmailId("bhavesh@lowes.com");
        contactBean.setPersonalEmailId("bhavesh@gmail.com");
        employeeBean.setContactBean(contactBean);
        employeeBeans.add(employeeBean);
        EmployeeBean employeeBean2=new EmployeeBean();
        employeeBean2.setFirstName("Bhavesh");
        employeeBean2.setLastName("S");
        ContactBean contactBean2=new ContactBean();
        contactBean2.setIsdCode(91l);
        contactBean2.setMobileNumber1*//*(9186453212l);
        contactBean2.setOfficeNumber(8084232332212l);
        contactBean2.setOfficialEmailId("bhavesh@lowes.com");
        contactBean2.setPersonalEmailId("bhavesh@gmail.com");
        employeeBean2.setContactBean(contactBean);
        employeeBeans.add(employeeBean2);
        meetingRoomBean.setEmployeeBeanList(employeeBeans);
        EmployeeBean organizer=new EmployeeBean();
        organizer.setFirstName("Akansha");
        organizer.setLastName("Verma");
        organizer.setEmployeeId(123412131l);
        ContactBean organizeContactNean=new ContactBean();
        organizeContactNean.setIsdCode(91l);
        organizeContactNean.setMobileNumber1(928232321121l);
        organizeContactNean.setOfficialEmailId("akansha@lowes.com");
        organizeContactNean.setPersonalEmailId("askansh@gmail.com");
        organizeContactNean.setOfficeNumber(803231231212l);
        organizer.setContactBean(organizeContactNean);
        meetingRoomBean.setOrganizer(organizer);
        Gson gson=new Gson();
        System.out.println(gson.toJson(meetingRoomBean));
       // meetingRoomBean.sert
    }*/

}
