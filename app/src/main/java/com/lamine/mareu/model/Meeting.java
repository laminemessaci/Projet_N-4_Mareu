package com.lamine.mareu.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public class Meeting implements Comparable<Meeting>{

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
     * Constructor
     * @param topic topic of the meeting
     * @param roomName name of the meeting room
     * @param start meeting start date and time
     * @param end meeting end date and time
     * @param participants list of email addresses of meeting participants
     */
    public Meeting(String roomName, Calendar start, Calendar end, String topic, List<String> participants) {
        mId = ++sLastId;
        mRoomName = roomName;
        mStart = start;
        mEnd = end;
        mTopic = topic;
        mParticipants = new ArrayList<>();
        this.setParticipants(participants);
        // Generate random color
        mColor = Color.argb(
                sRandom.nextInt(255),
                sRandom.nextInt(255),
                sRandom.nextInt(255),
                sRandom.nextInt(255));
    }

    public Integer getId() {
        return mId;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public Calendar getStart() {
        return mStart;
    }

    public Calendar getEnd() {
        return mEnd;
    }

    public String getTopic() {
        return mTopic;
    }

    public List<String> getParticipants() {
        return mParticipants;
    }

    public Integer getColor() {
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

    @Override
    public int compareTo(Meeting o) {
        if (getStart() == null || o.getStart() == null) {
            return 0;
        }

        return getStart().compareTo(o.getStart());
    }
}
