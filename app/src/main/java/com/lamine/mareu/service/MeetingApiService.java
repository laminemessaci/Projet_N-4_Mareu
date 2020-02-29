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

    /**
     * remove a meeting room
     * @param romm meeting room to delete
     */
    void delRoom(String romm);

    /**
     * remove all meeting rooms
     * source path to delete all meeting rooms
     */
    void delAllRooms();

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
    void addMeeting(Meeting meeting) throws MeetingApiServiceException;

    /**
     * remove a meeting with his Unique Identifier
     * @param idMeeting
     */
    public void delMeeting(Integer idMeeting) throws MeetingApiServiceException;
}
