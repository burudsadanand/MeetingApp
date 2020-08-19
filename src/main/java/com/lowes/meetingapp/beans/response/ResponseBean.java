package com.lowes.meetingapp.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBean<T> {

    private Meta meta;

    private T response;

    @JsonProperty("error")
    private BaseResponse errorResponse;

}