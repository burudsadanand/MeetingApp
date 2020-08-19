package com.lowes.meetingapp.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meta {

    private Status status;
    private Long responseTimeInMillis;
}
