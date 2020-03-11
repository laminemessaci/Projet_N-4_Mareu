package com.lamine.mareu;

import android.content.Context;
import android.util.Patterns;

import java.text.ParseException;

/**
 * Created by Lamine MESSACI on 10/03/2020.
 */
public class Validator {
    public static boolean validDate(Context context, String value) {
        try {
            android.text.format.DateFormat.getDateFormat(context).parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // TODO
    public static boolean validTime(Context context, String value) {
        try {
            android.text.format.DateFormat.getTimeFormat(context).parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean validEmail(String value) {
        return Patterns.EMAIL_ADDRESS.matcher(value.trim()).matches();
    }
}
