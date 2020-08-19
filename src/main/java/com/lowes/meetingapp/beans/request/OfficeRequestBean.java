package com.lowes.meetingapp.beans.request;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

import java.util.List;

@Getter
@Setter
public class OfficeRequestBean {

    private Long officeId;
    private String officeName;
    private List<OfficeFloorBean> officeFloors;
    private AddressBean address;
    private LocalDate createDate;
    private LocalDate lastUpdatedDate;
}
