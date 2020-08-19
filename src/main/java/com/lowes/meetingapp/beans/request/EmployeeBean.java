package com.lowes.meetingapp.beans.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lowes.meetingapp.beans.response.ContactDetailsBean;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeBean {

    private Long employeeId;
    private String firstName;
    private String lastName;
    @JsonProperty("contact")
    private ContactBean contactBean;
}
