package com.lowes.meetingapp.beans.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressBean {

    private String city;
    private String state;
    private String pinCode;
    private String country;
}
