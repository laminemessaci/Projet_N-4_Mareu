package com.lamine.mareu.model;

import android.graphics.Color;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public class Meeting {

    private static Integer sLastId = 0;
    private static Random sRandom = new Random();

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
        mId = ++sLastId; //unique identifier
        mRoomName = roomName;
        mStart = start;
        mEnd = end;
        mTopic = topic;
        mParticipants = participants;

        // Generate random color
        mColor = Color.argb(
                sRandom.nextInt(255),
                sRandom.nextInt(255),
                sRandom.nextInt(255),
                sRandom.nextInt(255));
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

    private void setParticipants(List<String> participants) {
        String email_pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(email_pattern, Pattern.CASE_INSENSITIVE);

        for (String participant : participants) {
            if (pattern.matcher(participant).matches()) {
                mParticipants.add(participant);
            }
        }
    }
}
