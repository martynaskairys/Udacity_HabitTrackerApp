package com.martynaskairys.udacity_habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitsDatabaseHelper habitsDatabaseHelper = new HabitsDatabaseHelper(this);

        //add records
        ContentValues contentValues = new ContentValues();
        contentValues.put(Habits.Entry.NAME," drink water ");
        contentValues.put(Habits.Entry.DATE_ADDED," 7.7.2017 ");
        contentValues.put(Habits.Entry.COUNT, 0);
        habitsDatabaseHelper.addRecord(contentValues);

        //update records
        contentValues.put(Habits.Entry.LAST_DAY_DONE, "8.7.2017");
        contentValues.put(Habits.Entry.COUNT, 1);
        habitsDatabaseHelper.updateRecord(contentValues);

        //get records
        habitsDatabaseHelper.getRecord(1);

        //delete records
        habitsDatabaseHelper.deleteRecord(1);

        //delete database
        habitsDatabaseHelper.deleteDatabase(this);


    }
}
