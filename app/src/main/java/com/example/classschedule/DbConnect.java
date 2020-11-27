package com.example.classschedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbConnect extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public DbConnect(@Nullable Context context) {
        super(context,"test.db",null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE 'Class' ('SubjectID' INTEGER PRIMARY KEY AUTOINCREMENT, 'SubjectName' TEXT, 'Location' TEXT, 'Classroom' TEXT, 'StartTime' TEXT, 'EndTime' TEXT,'Day' TEXT,'Date' TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
