package com.lowes.meetingapp.core.exception;

import com.lowes.meetingapp.core.constants.ErrorEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.concurrent.CompletionException;


@Getter
@Setter
@RequiredArgsConstructor
public class DAOException extends Exception {

    private String message;

    private ErrorEnum error;

    public DAOException(String message, ErrorEnum error) {
        super(message);
        this.error = error;
    }
}
