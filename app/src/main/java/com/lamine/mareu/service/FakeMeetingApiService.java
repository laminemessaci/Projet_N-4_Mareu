package com.lamine.mareu.service;

import com.lamine.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static com.lamine.mareu.MeetingUtil.getMeetingsMatchDate;
import static com.lamine.mareu.MeetingUtil.getMeetingsMatchRoomName;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public class FakeMeetingApiService implements MeetingApiService {


    private List<Meeting> mMeetings;
    private final List<String> mRooms;

    public FakeMeetingApiService() {

        mMeetings = new ArrayList<>();
        mRooms = new ArrayList<>(Arrays.asList(
                "Room 1", "Room 2", "Room 3", "Room 4", "Room 5",
                "Room 6", "Room 7", "Room 8", "Room 9", "Room 10"));
    }


    @Override
    public List<String> getRooms() {
        return mRooms;
    }

    @Override
    public void addRoom(String room) {
        mRooms.add(room);
    }

    @Override
    public void delRoom(String room) {
        mRooms.remove(room);
    }

    @Override
    public void delAllRooms() {
        mRooms.clear();
    }

    @Override
    public List<Meeting> getMeetings(Calendar date, String roomName) {

        if (date != null && roomName != null && !roomName.isEmpty())
            return getMeetingsMatchDate(date, getMeetingsMatchRoomName(roomName, mMeetings));
        else if (date != null)
            return getMeetingsMatchDate(date, mMeetings);
        else if (roomName != null && !roomName.isEmpty())
            return getMeetingsMatchRoomName(roomName, mMeetings);

        Collections.sort(mMeetings);
        return mMeetings;
    }

    @Override
    public void addMeeting(Meeting meeting) throws MeetingApiServiceException {
        // Check if the meeting room is free
        for (Meeting m : mMeetings) {
            if (meeting.getRoomName().equals(m.getRoomName())) {
                if (meeting.getStart().equals(m.getStart()) || meeting.getEnd().equals(m.getEnd()))
                    throw new MeetingApiServiceException();
                if (meeting.getStart().after(m.getStart()) && meeting.getStart().before(m.getEnd()))
                    throw new MeetingApiServiceException();
                if (meeting.getEnd().after(m.getStart()) && meeting.getEnd().before(m.getEnd()))
                    throw new MeetingApiServiceException();
            }
        }
        // Add meeting
        mMeetings.add(meeting);

    }

    @Override
    public void delMeeting(Integer idMeeting) throws MeetingApiServiceException {
        for (Meeting m : mMeetings) {
            if (m.getId().equals(idMeeting)) {
                mMeetings.remove(m);
                break;
            }
        }

    }
}
