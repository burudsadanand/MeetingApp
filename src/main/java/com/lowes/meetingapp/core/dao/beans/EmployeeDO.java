package com.lowes.meetingapp.core.dao.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDO {

    private Long employeeId;
    private Long officeId;
    private String firstName;
    private String lastName;
    private ContactDO contactDO;
    private AddressDO addressDO;


}
