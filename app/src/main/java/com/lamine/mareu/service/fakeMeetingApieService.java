package com.lamine.mareu.service;

import com.lamine.mareu.model.Meeting;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public class fakeMeetingApieService implements MeetingApiService {


    @Override
    public List<String> getRooms () {
        return null;
    }

    @Override
    public void addRoom (String romm) {

    }

    @Override
    public void deleteRoom (String romm) {

    }

    @Override
    public List<Meeting> getMeetings (Calendar date, String roomName) {
        return null;
    }

    @Override
    public void addMeeting (Meeting meeting) {

    }

    @Override
    public void removeMeeting (Integer idMeeting) {

    }
}
