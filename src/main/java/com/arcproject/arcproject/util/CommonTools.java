package com.arcproject.arcproject.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public final class CommonTools {
    private CommonTools(){

    }

    public static String convertStamp(double stamp){
        Instant instant = Instant.ofEpochSecond((long) stamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, TimeZone.getDefault().toZoneId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        
        return dateTime.format(formatter);
    }

    public static Map<String, Object> convertResults(Object object){
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("results", object);
        
        return jsonResponse;
    }
}
