package com.lowes.meetingapp.beans.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SlotsBean {

    private String from;
    private String to;

    public SlotsBean(){

    }

}
