package com.lowes.meetingapp.core.exception;

import com.lowes.meetingapp.core.constants.ErrorEnum;

import java.util.concurrent.CompletionException;

public class BusinessServiceException extends CompletionException {

    public ErrorEnum errorEnum;

    public String message;

    public BusinessServiceException(String message, ErrorEnum errorEnum) {
        super(message);
        this.errorEnum = errorEnum;
    }

    public BusinessServiceException(Throwable cause, ErrorEnum errorEnum) {
        super(cause);
        this.errorEnum = errorEnum;
    }
}
