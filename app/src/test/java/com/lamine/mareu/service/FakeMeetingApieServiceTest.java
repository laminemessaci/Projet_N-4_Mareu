package com.lamine.mareu.service;

import com.lamine.mareu.model.Meeting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Calendar;

import static com.lamine.mareu.utils.UtilDateFormt.fromTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Lamine MESSACI on 31/03/2020.
 */
public class FakeMeetingApieServiceTest {

    private FakeMeetingApiService mApi;
    private Integer mInitialCount;
    private Meeting mMeeting;

    @BeforeEach
    private void setUp() throws MeetingApiServiceException {
        mApi = new FakeMeetingApiService();

        mMeeting = new Meeting(
                "Salle 1",
                fromTime("14:00"),
                fromTime("15:00"),
                "sujet",
                Arrays.asList("p.roger@gmail.com", "s.ramen@gmail.fr"));

        // Initialize API with 1 Meeting
        mApi.addMeeting(mMeeting);

        mInitialCount = mApi.getMeetings(null, "").size();
    }

    @Test
    void addMeeting() {
        Meeting meeting = new Meeting(
                "Salle 2",
                Calendar.getInstance(),
                Calendar.getInstance(),
                "sujet",
                Arrays.asList("p.roger@gmail.com", "s.ramen@gmail.fr"));

        try {
            mApi.addMeeting(meeting);
        } catch (MeetingApiServiceException e) {

        }
    }

    @ParameterizedTest
    @CsvSource({"14:00,15:00", "13:30,15:00", "14:00,15:30", "14:15,14:45", "13:30,14:30", "14:30,15:30"})
    void addMeetingFail(String start, String end) {
        final Meeting meeting = new Meeting(
                "Salle 1",
                fromTime(start),
                fromTime(end),
                "sujet",
                Arrays.asList("p.roger@gmail.com", "s.ramen@gmail.fr"));

        assertThrows(MeetingApiServiceException.class, () -> mApi.addMeeting(meeting));
    }

    @Test
    void getMeetings() {
        assertEquals((int) mInitialCount, mApi.getMeetings(null, "").size());
    }

    @Test
    void delMeeting() throws MeetingApiServiceException {
        mApi.delMeeting(mMeeting.getId());

        assertEquals((int) --mInitialCount, mApi.getMeetings(null, "").size());
    }
}
