package com.lowes.meetingapp.beans.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse {

    private  Status status;
    private  ErrorCode errorCode;

    public  BaseResponse(){

    }
}
