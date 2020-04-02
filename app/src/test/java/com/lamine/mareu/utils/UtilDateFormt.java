package com.lamine.mareu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * Created by Lamine MESSACI on 31/03/2020.
 */
public class UtilDateFormt {
    public static Calendar fromTime(String time) {
        // convert string to time
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        // get now
        Calendar datetime = Calendar.getInstance();
        // set time
        try {
            datetime.setTime(Objects.requireNonNull(sdf.parse(time)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return datetime;
    }
}
