package com.lamine.mareu.model;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public class Meeting {

    private Integer mId;
    private String mRoomName;
    private Calendar mStart;
    private Calendar mEnd;
    private String mTopic;
    private List<String> mParticipants;
    private Integer mColor;

    /**
     *
     * @param roomName  name of Meeting Room
     * @param start     start date of meeting
     * @param end       end date of meeting
     * @param topic     topic of meeting
     * @param participants participants(emails) of meeting
     */

    public Meeting (String roomName, Calendar start, Calendar end, String topic, List<String> participants) {
        mRoomName = roomName;
        mStart = start;
        mEnd = end;
        mTopic = topic;
        mParticipants = participants;
    }


    public Integer getId () {
        return mId;
    }

    public String getRoomName () {
        return mRoomName;
    }

    public Calendar getStart () {
        return mStart;
    }

    public Calendar getEnd () {
        return mEnd;
    }

    public String getTopic () {
        return mTopic;
    }

    public List<String> getParticipants () {
        return mParticipants;
    }

    public Integer getColor () {
        return mColor;
    }
}
