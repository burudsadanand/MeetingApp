package com.lowes.meetingapp.beans.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchMeetingRequest {

    private List<String> emailIds;

    public static void main(String[] args){
        SearchMeetingRequest searchMeetingRequest=new SearchMeetingRequest();
        List<String> emails=new ArrayList<>();
        emails.add("sadanad@gmail.com");
        emails.add("dasda@gmail.com");
        searchMeetingRequest.setEmailIds(emails);
        Gson gson=new Gson();
        System.out.println(gson.toJson(searchMeetingRequest));
    }
}
