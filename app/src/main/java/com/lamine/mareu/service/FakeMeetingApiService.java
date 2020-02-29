package com.lamine.mareu.service;

import com.lamine.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public class FakeMeetingApiService implements MeetingApiService {


    private List<Meeting> mMeetings;
    private final List<String> mRooms;

    public FakeMeetingApiService () {

        mMeetings = new ArrayList<> ();
        mRooms = new ArrayList<>(Arrays.asList(
                "Room 1", "Room 2", "Room 3", "Room 4", "Room 5",
                "Room 6", "Room 7", "Room 8", "Room 9", "Room 10"));
    }



    @Override
    public List<String> getRooms () { return mRooms; }

    @Override
    public void addRoom (String room) { mRooms.add (room);
    }

    @Override
    public void delRoom (String room) { mRooms.remove (room); }

    @Override
    public void delAllRooms () {
        mRooms.clear ();
    }

    @Override
    public List<Meeting> getMeetings (Calendar date, String roomName) {
        //TO DO
        return mMeetings;
    }

    @Override
    public void addMeeting (Meeting meeting) throws MeetingApiServiceException {
        // TO DO

    }

    @Override
    public void delMeeting (Integer idMeeting) throws MeetingApiServiceException {
        // TO DO
    }


}