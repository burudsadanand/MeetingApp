package com.lowes.meetingapp.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.*;

public class IdGenerator {


    private static Map<String, Integer> slotMap;
    static {
        slotMap = new LinkedHashMap<>();
        Integer nextHour = 00;
        Integer nextMinute = 00;
        slotMap.put("0"+nextHour + ":" + nextMinute+"0", 0);
        for (int i = 1; i <= 95; i++) {
            if (i % 4 == 0) {
                nextHour++;
                nextMinute = 00;
                if(nextHour<10){
                    slotMap.put("0"+nextHour + ":" + nextMinute+"0", i);
                }else{
                    slotMap.put(nextHour + ":" + nextMinute+"0", i);
                }

            } else {
                nextMinute += 15;
                if(nextHour>10 && nextHour%10>0){
                    slotMap.put(nextHour + ":" + nextMinute, i);
                }else if(nextHour<10){
                    slotMap.put("0"+nextHour + ":" + nextMinute, i);
                }else{
                    slotMap.put(nextHour + ":" + nextMinute, i);
                }

            }


        }
    }
    public static List<String> generateOfficeIntenvetoryId(Long officeId, LocalDate fromDate,
                                                           LocalDate toDate){

        List<String> keyList = new ArrayList<>();
        DateTime fromDateTime = fromDate.toDateTimeAtStartOfDay();
        DateTime endDateTime= toDate.toDateTimeAtStartOfDay();
        String seperator="#";
        while(fromDateTime.isBefore(endDateTime) || fromDateTime.equals(endDateTime)) {
            StringBuilder builder=new StringBuilder();
            builder.append(officeId);
            builder.append(seperator);
            builder.append(fromDateTime.toLocalDate().toString());
            keyList.add(builder.toString());
            fromDateTime = fromDateTime.plusDays(1);
        }
        return keyList;

    }

    public static Map<String,Integer> getSlotMap(){
        return slotMap;
    }

    public static Long generateMeetingId(){
        Random random=new Random();
        return Math.abs(random.nextLong());
    }

   /* public static void main(String[] args){
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusMonths(2);
        List<String> listOfKeys=generateOfficeIntenvetoryId(12345l, fromDate,toDate);
        for(String date:listOfKeys){
            System.out.println("date Key is =="+date);
        }
    }*/


}
