package com.lowes.meetingapp.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class DateUtils {


    public static LocalDate convertDateStringToLocalDate(String dateString){

        LocalDate localDate = LocalDate.parse(dateString.trim());

        return localDate;

    }


    public static List<LocalDate> generateCaledarDates(LocalDate fromDate, LocalDate toDate){
        List<LocalDate> localDates=new ArrayList<>();
        DateTime fromDateTime =fromDate.toDateTimeAtStartOfDay();
        DateTime endDateTime= toDate.toDateTimeAtStartOfDay();
        while(fromDateTime.isBefore(endDateTime) || fromDateTime.equals(endDateTime)) {
            localDates.add(fromDateTime.toLocalDate());
            fromDateTime = fromDateTime.plusDays(1);
        }
        return localDates;
    }

    public static String convertDateToString(LocalDate localDate) {
        return localDate.toString("yyyy-MM-dd");
    }
}
