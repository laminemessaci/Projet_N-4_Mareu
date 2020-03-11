package com.lamine.mareu;

import java.util.Calendar;

/**
 * Created by Lamine MESSACI on 10/03/2020.
 */
public class CalendarUtil {

    public static boolean sameDate(Calendar firstDate, Calendar secondDate) {
        if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
            if (firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH))
                return firstDate.get(Calendar.DAY_OF_MONTH) == secondDate.get(Calendar.DAY_OF_MONTH);

        return false;
    }

    public static boolean beforeOrSameDate(Calendar firstDate, Calendar secondDate) {
        if (firstDate.get(Calendar.YEAR) < secondDate.get(Calendar.YEAR))
            return true;
        if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
            if (firstDate.get(Calendar.MONTH) < secondDate.get(Calendar.MONTH))
                return true;
        if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
            if (firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH))
                return firstDate.get(Calendar.DAY_OF_MONTH) <= secondDate.get(Calendar.DAY_OF_MONTH);

        return false;
    }

    public static boolean beforeDate(Calendar firstDate, Calendar secondDate) {
        if (firstDate.get(Calendar.YEAR) < secondDate.get(Calendar.YEAR))
            return true;
        if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
            if (firstDate.get(Calendar.MONTH) < secondDate.get(Calendar.MONTH))
                return true;
        if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
            if (firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH))
                return firstDate.get(Calendar.DAY_OF_MONTH) < secondDate.get(Calendar.DAY_OF_MONTH);

        return false;
    }

    public static boolean afterOrSameDate(Calendar firstDate, Calendar secondDate) {
        if (firstDate.get(Calendar.YEAR) > secondDate.get(Calendar.YEAR))
            return true;
        if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
            if (firstDate.get(Calendar.MONTH) > secondDate.get(Calendar.MONTH))
                return true;
        if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
            if (firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH))
                return firstDate.get(Calendar.DAY_OF_MONTH) >= secondDate.get(Calendar.DAY_OF_MONTH);

        return false;
    }

    public static boolean afterDate(Calendar firstDate, Calendar secondDate) {
        if (firstDate.get(Calendar.YEAR) > secondDate.get(Calendar.YEAR))
            return true;
        if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
            if (firstDate.get(Calendar.MONTH) > secondDate.get(Calendar.MONTH))
                return true;
        if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
            if (firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH))
                return firstDate.get(Calendar.DAY_OF_MONTH) > secondDate.get(Calendar.DAY_OF_MONTH);

        return false;
    }

    private static int compareDate(Calendar firstDate, Calendar secondDate){
        if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
            if (firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH))
                if (firstDate.get(Calendar.DAY_OF_MONTH) == secondDate.get(Calendar.DAY_OF_MONTH))
                    return 0;
                else if (firstDate.get(Calendar.DAY_OF_MONTH) > secondDate.get(Calendar.DAY_OF_MONTH))
                    return 1;
                else
                    return -1;
            else if (firstDate.get(Calendar.MONTH) > secondDate.get(Calendar.MONTH))
                return 1;
            else
                return -1;
        else if (firstDate.get(Calendar.YEAR) > secondDate.get(Calendar.YEAR))
            return 1;
        else
            return -1;
    }

    public static boolean sameTime(Calendar firstTime, Calendar secondTime) {
        if (firstTime.get(Calendar.HOUR_OF_DAY) == secondTime.get(Calendar.HOUR_OF_DAY))
            return firstTime.get(Calendar.MINUTE) == secondTime.get(Calendar.MINUTE);

        return false;
    }

    public static boolean beforeOrSameTime(Calendar firstTime, Calendar secondTime) {
        if (firstTime.get(Calendar.HOUR_OF_DAY) < secondTime.get(Calendar.HOUR_OF_DAY))
            return true;
        if (firstTime.get(Calendar.HOUR_OF_DAY) == secondTime.get(Calendar.HOUR_OF_DAY))
            return firstTime.get(Calendar.MINUTE) <= secondTime.get(Calendar.MINUTE);

        return false;
    }

    public static boolean beforeTime(Calendar firstTime, Calendar secondTime) {
        if (firstTime.get(Calendar.HOUR_OF_DAY) < secondTime.get(Calendar.HOUR_OF_DAY))
            return true;
        if (firstTime.get(Calendar.HOUR_OF_DAY) == secondTime.get(Calendar.HOUR_OF_DAY))
            return firstTime.get(Calendar.MINUTE) < secondTime.get(Calendar.MINUTE);

        return false;
    }

    public static boolean afterOrSameTime(Calendar firstTime, Calendar secondTime) {
        if (firstTime.get(Calendar.HOUR_OF_DAY) > secondTime.get(Calendar.HOUR_OF_DAY))
            return true;
        if (firstTime.get(Calendar.HOUR_OF_DAY) == secondTime.get(Calendar.HOUR_OF_DAY))
            return firstTime.get(Calendar.MINUTE) >= secondTime.get(Calendar.MINUTE);

        return false;
    }

    public static boolean afterTime(Calendar firstTime, Calendar secondTime) {
        if (firstTime.get(Calendar.HOUR_OF_DAY) > secondTime.get(Calendar.HOUR_OF_DAY))
            return true;
        if (firstTime.get(Calendar.HOUR_OF_DAY) == secondTime.get(Calendar.HOUR_OF_DAY))
            return firstTime.get(Calendar.MINUTE) > secondTime.get(Calendar.MINUTE);

        return false;
    }

    private static int compareTime(Calendar firstTime, Calendar secondTime){
        if (firstTime.get(Calendar.HOUR_OF_DAY) == secondTime.get(Calendar.HOUR_OF_DAY))
            if (firstTime.get(Calendar.MINUTE) == secondTime.get(Calendar.MINUTE))
                return 0;
            else if (firstTime.get(Calendar.MINUTE) > secondTime.get(Calendar.MINUTE))
                return 1;
            else
                return -1;
        else if (firstTime.get(Calendar.HOUR_OF_DAY) > secondTime.get(Calendar.HOUR_OF_DAY))
            return 1;
        else
            return -1;
    }
}
