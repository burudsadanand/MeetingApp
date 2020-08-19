package com.lowes.meetingapp.core.dao.beans;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizerDO {

    private Long id;
    private String name;
    private ContactDO contactInfo;
    private AddressDO addressInfo;

}
