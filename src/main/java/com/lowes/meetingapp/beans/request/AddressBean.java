package com.lowes.meetingapp.beans.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBean {

    private String city;
    private String state;
    private String pinCode;
    private String country;
}
