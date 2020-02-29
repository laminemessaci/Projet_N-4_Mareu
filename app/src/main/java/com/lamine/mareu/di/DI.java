package com.lamine.mareu.di;

import com.lamine.mareu.service.FakeMeetingApiService;
import com.lamine.mareu.service.MeetingApiService;

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


}
