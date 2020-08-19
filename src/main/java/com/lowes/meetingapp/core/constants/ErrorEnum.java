package com.lowes.meetingapp.core.constants;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    ENTITY_EXISTS(1017,"Object Already Exists"),ENTITY_NOTEXISTS(1018,"Record does not Exists"),INTERSERVER_ERROR(500,"Service Error"),
    ERROR_ADDING_INVENTORY(1011,"Error adding Inventory");

    private  Integer errorCode;
    private  String errorMessage;

    ErrorEnum(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
