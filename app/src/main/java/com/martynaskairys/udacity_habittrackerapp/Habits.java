package com.martynaskairys.udacity_habittrackerapp;

import android.provider.BaseColumns;

/**
 * Created by martynaskairys on 25/06/2017.
 */

public class Habits {

    public static final class Entry implements BaseColumns{

        public static final String TABLE_NAME = "habits";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DATE_ADDED = "date_added";
        public static final String LAST_DAY_DONE = "last_day_done";
        public static final String COUNT = "count";

    }

}

