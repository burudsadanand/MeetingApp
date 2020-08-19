package com.lowes.meetingapp.beans.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBean<T> {

    private Meta meta;

    private T response;

    private BaseResponse errorResponse;

}