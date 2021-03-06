package com.lowes.meetingapp.beans.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SERVICE_ERROR(1001,"Room Registration failed"),EMPLOYEE_UNAVAILBLE(2010,"Employee is unavailable on mentioned Slot");

    private Integer errorCode;
    private String errorMessge;
}
