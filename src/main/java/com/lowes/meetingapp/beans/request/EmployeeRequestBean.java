package com.lowes.meetingapp.beans.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeRequestBean {

    private Long employeeId;
    private String firstName;
    private String lastName;
    @JsonProperty("contact")
    private ContactBean contactBean;
    @JsonProperty("address")
    private AddressBean addressBean;

//    public static void main(String[] args){
//        EmployeeRequestBean employeeBean=new EmployeeRequestBean();
//        employeeBean.setEmployeeId(123412131l);
//        employeeBean.setFirstName("Akansha");
//        employeeBean.setLastName("Verma");
//        ContactBean contactBean=new ContactBean();
//        contactBean.setOfficeNumber(803231231212l);
//        contactBean.setPersonalEmailId("askansh@gmail.com");
//        contactBean.setOfficialEmailId("akansha@lowes.com");
//        contactBean.setMobileNumber1(928232321121l);
//        contactBean.setIsdCode(91l);
//        AddressBean addressBean=new AddressBean();
//        addressBean.setState("Karnataka");
//        addressBean.setPinCode("560063");
//        addressBean.setCountry("India");
//        addressBean.setCity("Bangalore");
//        employeeBean.setContactBean(contactBean);
//        employeeBean.setAddressBean(addressBean);
//
//
//
//        EmployeeRequestBean employeeBean2=new EmployeeRequestBean();
//        employeeBean2.setEmployeeId(123412136l);
//        employeeBean2.setFirstName("Vignesh");
//        employeeBean2.setLastName("S");
//        ContactBean contactBean2=new ContactBean();
//        contactBean2.setOfficeNumber(53232332212l);
//        contactBean2.setPersonalEmailId("vigneshh@gmail.com");
//        contactBean2.setOfficialEmailId("vigneshh@lowes.com");
//        contactBean2.setMobileNumber1(9321453212l);
//        contactBean2.setIsdCode(91l);
//        AddressBean addressBean2=new AddressBean();
//        addressBean2.setState("Karnataka");
//        addressBean2.setPinCode("560063");
//        addressBean2.setCountry("India");
//        addressBean2.setCity("Bangalore");
//        employeeBean2.setContactBean(contactBean);
//        employeeBean2.setAddressBean(addressBean);
//
//
//        EmployeeRequestBean employeeBean3=new EmployeeRequestBean();
//        employeeBean3.setEmployeeId(123412134l);
//        employeeBean3.setFirstName("Bhavesh");
//        employeeBean3.setLastName("S");
//        ContactBean contactBean3=new ContactBean();
//        contactBean3.setOfficeNumber(8084232332212l);
//        contactBean3.setPersonalEmailId("bhavesh@gmail.com");
//        contactBean3.setOfficialEmailId("bhavesh@lowes.com");
//        contactBean3.setMobileNumber1(9186453212l);
//        contactBean3.setIsdCode(91l);
//        AddressBean addressBean3=new AddressBean();
//        addressBean3.setState("Karnataka");
//        addressBean3.setPinCode("560063");
//        addressBean3.setCountry("India");
//        addressBean3.setCity("Bangalore");
//        employeeBean3.setContactBean(contactBean);
//        employeeBean3.setAddressBean(addressBean);
//
//        Gson gson1=new Gson();
//        System.out.println(gson1.toJson(employeeBean));
//        Gson gson2=new Gson();
//        System.out.println(gson2.toJson(employeeBean2));
//        Gson gson3=new Gson();
//        System.out.println(gson3.toJson(employeeBean3));
//
//
//
//
//    }
}
