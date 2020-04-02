package com.lamine.mareu.di;

import com.lamine.mareu.model.Meeting;
import com.lamine.mareu.service.FakeMeetingApiService;
import com.lamine.mareu.service.MeetingApiService;
import com.lamine.mareu.service.MeetingApiServiceException;

import java.util.List;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public class DI {

    private static MeetingApiService sService = new FakeMeetingApiService ();

    /**
     * Get instance on @(link MeetingApieService)
     * @return FakeMeetingApiService()
     */
    public static MeetingApiService getApiService () {
        return sService;
    }

    /**
     * Initialize FakeMeetingApiService for tests
     *
     * @param meetings list of meetings
     */
    public static void initializeMeetingApiService(List<String> rooms, List<Meeting> meetings) throws MeetingApiServiceException {
        // Purge
        sService = new FakeMeetingApiService();
        sService.delAllRooms();

        // Initialize
        for (String room: rooms)
            sService.addRoom(room);

        for (Meeting meeting: meetings)
            sService.addMeeting(meeting);
    }


}
