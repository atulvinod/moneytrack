package com.atulvinod.moneytrack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class get_datetime {
    Calendar calendar;

    public get_datetime() {
        calendar = Calendar.getInstance();
    }

    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public  String getDayOfMonth() {


        return "" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    public String getMonth() {


        return "" + calendar.get(Calendar.MONTH);
    }

    public String getYear() {
        return "" + calendar.get(Calendar.YEAR);
    }
}



