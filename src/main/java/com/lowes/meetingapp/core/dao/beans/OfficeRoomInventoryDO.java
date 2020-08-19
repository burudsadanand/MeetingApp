package com.lowes.meetingapp.core.dao.beans;


import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

import java.util.List;

@Getter
@Setter
public class OfficeRoomInventoryDO {

    private Long officeId;
    private String officeName;
    private List<OfficeFloorDO> officeFloors;
    private AddressDO address;
    private LocalDate createDate;
    private LocalDate lastUpdatedDate;
    private LocalDate fromDate;
    private LocalDate toDate;

}
