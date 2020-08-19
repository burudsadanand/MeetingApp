package com.lowes.meetingapp.beans.request;

import com.lowes.meetingapp.beans.response.ContactDetailsBean;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeBean {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private ContactBean contactBean;
}
