package com.martynaskairys.udacity_habittrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by martynaskairys on 25/06/2017.
 */

public class HabitsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "habitsDatabase";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    public HabitsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " +
                        Habits.Entry.TABLE_NAME +
                        "(" +
                        Habits.Entry.NAME + "," +
                        Habits.Entry.DATE_ADDED + "," +
                        Habits.Entry.LAST_DAY_DONE + "," +
                        Habits.Entry.COUNT + ")";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Habits.Entry.TABLE_NAME);
        onCreate(db);
    }


    public void deleteRecord(int recordId) {

        db = getWritableDatabase();
        try {
            db.delete(
                    Habits.Entry.TABLE_NAME,
                    "=" + recordId,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    public void addRecord(ContentValues contentValues) {

        db = getWritableDatabase();
        try {
            db.insert(
                    Habits.Entry.TABLE_NAME,
                    null,
                    contentValues
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    public void updateRecord(ContentValues contentValues) {

        db = getWritableDatabase();
        try {
            db.update(
                    Habits.Entry.TABLE_NAME,
                    contentValues,
                    null,
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    public Cursor getRecord(int recordId) {

        Cursor record;
        String table = Habits.Entry.TABLE_NAME;
        String[] selectionArg = new String[]{Integer.toString(recordId)};

        db = getReadableDatabase();
        try {
            record = db.query(true, table, null, null, selectionArg,
                    null, null, null, null);
            record.moveToFirst();
            record.close();
            db.close();
            return record;
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
            return null;
        }

    }

    public Cursor readRecord() {

        db = getReadableDatabase();
        String[] reading = {
                Habits.Entry.NAME,
                Habits.Entry.DATE_ADDED,
                Habits.Entry.LAST_DAY_DONE,
        };

        Cursor cursor = db.query(Habits.Entry.TABLE_NAME, reading, null,
                null, null, null, null);

        return cursor;
    }

    public void deleteDatabase(Context context) {

        try {
            context.deleteDatabase(DB_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
