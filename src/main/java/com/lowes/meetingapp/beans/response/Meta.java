package com.lowes.meetingapp.beans.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {

    private Status status;
    private Long responseTimeInMillis;
}
