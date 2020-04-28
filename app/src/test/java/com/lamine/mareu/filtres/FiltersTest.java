package com.lamine.mareu.filtres;


import com.lamine.mareu.model.Meeting;
import com.lamine.mareu.service.FakeMeetingApiService;
import com.lamine.mareu.utils.MeetingUtil;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.lamine.mareu.utils.UtilDateFormt.fromTime;
import static com.lamine.mareu.utils.UtilDateFormt.generateDateFromNow;
import static com.lamine.mareu.utils.UtilDateFormt.generateDateTimeFromTomorrow;
import static com.lamine.mareu.utils.UtilDateFormt.initRoundedNow;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Created by Lamine MESSACI on 27/04/2020.
 */
public class FiltersTest {


    List<Meeting> meetings = new ArrayList<>();
    FakeMeetingApiService mApiService = new FakeMeetingApiService();
    private static final Calendar NOW = initRoundedNow();

    List<String> participants  = Arrays.asList(
            "p.roger@gmail.com",
            "s.ramen@gmail.fr");

    Meeting meeting1 = new Meeting("Room 1",
            fromTime("11:00"),
            fromTime("11:30"),
            "Just a meeting",
            participants);

    Meeting meeting2 = new Meeting("Room 2",
            fromTime("14:00"),
            fromTime("14:30"),
            "Just a meeting",
            participants);

    Meeting meeting3 = new Meeting("Room 3",
            fromTime("17:00"),
            fromTime("17:30"),
            "Just a meeting",
            participants);


    @Test
    public void sortMeetingsByRoomNameWithSucess() {

        meetings.add(meeting2);
        meetings.add(meeting1);
        meetings.add(meeting3);

        assertEquals(meeting1, meetings.get(1));
        assertEquals(meeting2, meetings.get(0));
        assertEquals(meeting3, meetings.get(2));

     // return only one in three
        assertEquals(meeting3, (MeetingUtil.getMeetingsMatchRoomName("Room 3", meetings).get(0)));

    }

    @Test
    public void sortMeetingsByDateWithSucess() {

        Meeting meeting1 = new Meeting("Room 1",
                generateDateTimeFromTomorrow(1, 10, 15),
                generateDateTimeFromTomorrow(1, 10, 45),
                "Just a meeting",
                participants);

        Meeting meeting2 = new Meeting("Room 2",
                fromTime("14:00"),
                fromTime("14:30"),
                "Just a meeting",
                participants);

        Meeting meeting3 = new Meeting("Room 3",
                fromTime("17:00"),
                fromTime("17:30"),
                "Just a meeting",
                participants);

        meetings.add(meeting2);
        meetings.add(meeting1);
        meetings.add(meeting3);

      //we make sure that only one in three remains
        assertEquals(1, (MeetingUtil.getMeetingsMatchDate(generateDateFromNow(1), meetings).size()));
    }


}
