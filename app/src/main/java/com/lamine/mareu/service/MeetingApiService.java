package com.lamine.mareu.service;

import com.lamine.mareu.model.Meeting;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public interface MeetingApiService {

    /**
     * get all meeting rooms;
     * @return List of meeting rooms
     */
    List<String> getRooms();

    /**
     * Add a meeting room
     * @param romm room of meeting to add
     */
    void addRoom(String romm);

    void deleteRoom(String romm);

    /**
     * get Meeting
     * @param date  filtered by date
     * @param roomName filtered by roomName
     * @return list of Meeting
     */
    List<Meeting> getMeetings(Calendar date, String roomName);

    /**
     * add meeting
     * @param meeting meeting to add
     */
    void addMeeting(Meeting meeting);

    /**
     * Remove a meeting
     * @param idMeeting meeting to delete using unique identifier
     */

    void removeMeeting(Integer idMeeting);


}
