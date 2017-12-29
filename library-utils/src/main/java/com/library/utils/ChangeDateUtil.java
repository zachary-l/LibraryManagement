package com.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDateUtil {
    public static Date StringToDate(String name) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYY/mm/dd");
        try {
            date = format.parse(name);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
