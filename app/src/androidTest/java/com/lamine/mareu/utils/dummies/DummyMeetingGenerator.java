package com.lamine.mareu.utils.dummies;

import com.lamine.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lamine.mareu.utils.dummies.DummyCalendarGenerator.generateDateTimeFromTomorrow;
import static com.lamine.mareu.utils.dummies.DummyCalendarGenerator.generateTomorrowDateTime;
import static com.lamine.mareu.utils.dummies.DummyEmailGenerator.generateEmails;

/**
 * Created by Lamine MESSACI on 27/03/2020.
 */
public  class DummyMeetingGenerator {
    public static final String ROOM_NAME_2 = "Room 2";
    public static final String ROOM_NAME = "Room 3";
    public static final String TOPIC = "Sprint review";
    public static int ITEMS_COUNT = 12;
    public static final String ROOM_NAME_1 = "Room 1";
    public static int TOMORROW_MEETINGS_COUNT = 8;
    public static int MEETINGS_WITH_ROOM_NAME_1_COUNT = 3;
    public static int TOMORROW_MEETING_WITH_ROOM_NAME_1 = 2;
    public static final int EXPECTED_ITEM_POSITION = 0;
    public static final int DELETE_ITEM_POSITION = 3;
    public static final String EXPECTED_DESCRIPTION_24H = "Room 1 - 9:00 - Mêlée";
    public static final String EXPECTED_DESCRIPTION_12H = "Room 1 - 9:00 AM - Mêlée";
    public static final String EXPECTED_PARTICIPANTS = String.join(", ", generateEmails());

    private static final List<String> DUMMY_MEETING_ROOMS = Arrays.asList(
            "Room 1", "Room 2", "Room 3", "Room 4", "Room 5",
            "Room 6", "Room 7", "Room 8", "Room 9", "Room 10");

    private static final List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Room 1",
                    generateTomorrowDateTime(9,0),
                    generateTomorrowDateTime(9, 15),
                    "Mêlée",
                    generateEmails()),
            new Meeting("Room 2",
                    generateTomorrowDateTime(9,0),
                    generateTomorrowDateTime(10,0),
                    "PMAD client",
                    generateEmails(1)),
            new Meeting("Room 3",
                    generateTomorrowDateTime(9,30),
                    generateTomorrowDateTime(10,30),
                    "Visioconférence RH",
                    generateEmails(3)),
            new Meeting("Room 1",
                    generateTomorrowDateTime(10,0),
                    generateTomorrowDateTime(10,30),
                    "Clôture d'incident avec le client",
                    generateEmails(2)),
            new Meeting("Room 4",
                    generateTomorrowDateTime(10,0),
                    generateTomorrowDateTime(10,45),
                    "Prospection téléphonique",
                    generateEmails(1)),
            new Meeting("Room 7",
                    generateTomorrowDateTime(10,0),
                    generateTomorrowDateTime(12,0),
                    "Formation des prestataires",
                    generateEmails()),
            new Meeting("Room 3",
                    generateTomorrowDateTime(14,0),
                    generateTomorrowDateTime(14,30),
                    "Visioconférence Client",
                    generateEmails(2)),
            new Meeting("Room 2",
                    generateTomorrowDateTime(16,0),
                    generateTomorrowDateTime(16,45),
                    "Entretien de parcours individuel",
                    generateEmails(2)),
            new Meeting("Room 1",
                    generateDateTimeFromTomorrow(2, 9, 0),
                    generateDateTimeFromTomorrow(2, 9, 15),
                    "Mêlée",
                    generateEmails()),
            new Meeting("Room 6",
                    generateDateTimeFromTomorrow(2, 14, 0),
                    generateDateTimeFromTomorrow(2, 17, 0),
                    "Revue de Sprint",
                    generateEmails()),
            new Meeting("Room 8",
                    generateDateTimeFromTomorrow(5, 9,0),
                    generateDateTimeFromTomorrow(5, 9, 15),
                    "Mêlée",
                    generateEmails()),
            new Meeting("Room 8",
                    generateDateTimeFromTomorrow(5, 9,30),
                    generateDateTimeFromTomorrow(5, 11, 30),
                    "Rétrospective de sprint",
                    generateEmails(3))
    );

    /**
     * Generate list of meeting rooms
     * @return list of mmeeting rooms
     */
    public static List<String> generateRooms() {
        return new ArrayList<>(DUMMY_MEETING_ROOMS);
    }

    /**
     * Generate list of meetings
     * @return list of mmeetings
     */
    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
