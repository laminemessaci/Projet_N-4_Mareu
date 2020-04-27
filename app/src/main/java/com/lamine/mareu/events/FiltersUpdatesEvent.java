package com.lamine.mareu.events;

import java.util.Calendar;

/**
 * Created by Lamine MESSACI on 25/03/2020.
 */
public class FiltersUpdatesEvent {
    public Calendar date;
    public String room;
    public boolean reset;

    public FiltersUpdatesEvent(Calendar date, String room, boolean reset) {
        this.date = date;
        this.room = room;
        this.reset = reset;

    }
}
