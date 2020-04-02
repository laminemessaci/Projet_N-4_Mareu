package com.lamine.mareu.utils.dummies;

import java.util.Calendar;

/**
 * Created by Lamine MESSACI on 27/03/2020.
 */
public class DummyCalendarGenerator {
    private static final Calendar NOW = initRoundedNow();
    public static final Calendar TOMORROW = generateDateFromNow(1);

    public static final Calendar YESTERDAY = generateDateFromNow(-1);

    public static final Calendar TODAY_INVALID_START_TIME = generateTimeFromNow(-1);

    public static final Calendar TOMORROW_VALID_START_TIME = (Calendar) TOMORROW.clone();
    public static final Calendar TOMORROW_VALID_END_TIME = generateTimeFromTomorrow(1);
    public static final Calendar TOMORROW_INVALID_END_TIME = generateTimeFromTomorrow(-1);

    /**
     * Generate Calendar for today with the time rounded up to the next half hour
     * @return today date with the time rounded up to the next half hour
     */
    private static Calendar initRoundedNow() {
        Calendar tmp = Calendar.getInstance();

        int roundedMinutes = tmp.get(Calendar.MINUTE) % 15;
        roundedMinutes = roundedMinutes > 0 ? (15 - roundedMinutes) : 0;
        roundedMinutes += 15;

        tmp.add(Calendar.MINUTE, roundedMinutes);

        return tmp;
    }

    /**
     * Generate a Calendar for a date from the past or the future
     * @param diffDays number of days to add or remove
     * @return past or future datetime
     */
    private static Calendar generateDateFromNow(int diffDays) {
        Calendar tmp = (Calendar) NOW.clone();
        tmp.add(Calendar.DATE, diffDays);

        return tmp;
    }

    /**
     * Generate a Calendar for an hour from the past or the future
     * @param diffHours number of hours to add or remove
     * @return past or future datetime
     */
    private static Calendar generateTimeFromNow(int diffHours) {
        Calendar tmp = (Calendar) NOW.clone();
        tmp.add(Calendar.HOUR_OF_DAY, diffHours);

        return tmp;
    }

    /**
     * Generate a Calendar for an hour and one day from the past or the future
     * @param diffHours number of hours to add or remove
     * @return past or future datetime
     */
    private static Calendar generateTimeFromTomorrow(int diffHours) {
        Calendar tmp = (Calendar) TOMORROW.clone();
        tmp.add(Calendar.HOUR_OF_DAY, diffHours);

        return tmp;
    }

    public static Calendar generateDateTimeFromTomorrow(int diffDays, int hours, int minutes) {
        Calendar tmp = (Calendar) TOMORROW.clone();

        if (diffDays > 1) {
            tmp.add(Calendar.DATE, diffDays);
        }

        tmp.set(Calendar.HOUR_OF_DAY, hours);
        tmp.set(Calendar.MINUTE, minutes);

        return tmp;
    }

    public static Calendar generateTomorrowDateTime(int hours, int minutes) {
        return generateDateTimeFromTomorrow(0, hours, minutes);
    }
}
