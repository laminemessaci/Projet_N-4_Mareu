package com.lamine.mareu.events;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public class DeleteMeetingEvent {
    private int meetingId;

    /**
     * constructor
     * @param meetingId unique identifier for meeting it will used for remove
     */
    public DeleteMeetingEvent (int meetingId) {
        this.meetingId = meetingId;
    }

    public int getMeetingId () {
        return meetingId;
    }
}
