package com.lamine.mareu.utils;

import com.lamine.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static com.lamine.mareu.utils.DateUtil.sameDate;

/**
 * Created by Lamine MESSACI on 10/03/2020.
 */
public class MeetingUtil {
    /**
     * Return ordered meetings list filter by date
     * @param date selected date
     * @param meetings meetings list to filter
     * @return filtered meetings list
     */
    public static List<Meeting> getMeetingsMatchDate(Calendar date, List<Meeting> meetings) {
        List<Meeting> tmp = new ArrayList<>();

        for (Meeting m: meetings)
            if (sameDate(m.getStart(), date))
                tmp.add(m);


        Collections.sort(tmp);

        return tmp;
    }

    /**
     * Return ordered meetings list filter by room name
     * @param roomName selected room name
     * @param meetings meetings list to filter
     * @return filtered meetings list
     */
    public static List<Meeting> getMeetingsMatchRoomName(String roomName, List<Meeting> meetings) {
        List<Meeting> tmp = new ArrayList<>();

        for (Meeting m: meetings)
            if (m.getRoomName().trim().equals(roomName.trim()))
                tmp.add(m);

        Collections.sort(tmp);

        return tmp;
    }
}
