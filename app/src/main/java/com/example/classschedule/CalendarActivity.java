package com.example.classschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SyncRequest;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

public class CalendarActivity extends AppCompatActivity {
    Toolbar toolbar3;
    MCalendarView calendarView ;
    TextView SubjectText,LocationText,ClassroomText,StartTimeText,EndTimeText,DateText;


    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_main,menu);
//        return true;
//
//    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.clearSubject:
//                DbConnect dbConnect = new DbConnect(this);
//                SQLiteDatabase sqLiteDatabase = dbConnect.getWritableDatabase();
//                sqLiteDatabase.execSQL("DELETE FROM Class");
//                Intent intent3 = new Intent(this,MainActivity.class);
//                startActivity(intent3);
//                Toast.makeText(this,  "Clear schedule success" , Toast.LENGTH_LONG).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
//        this.toolbar3 = findViewById(R.id.toolbar);
//        toolbar3.setTitle("ตารางเรียน");
        this.calendarView = (MCalendarView)findViewById(R.id.calendarView);
        this.SubjectText = findViewById(R.id.textView5);
        this.LocationText = findViewById(R.id.textView7);
        this.ClassroomText = findViewById(R.id.textView9);
        this.StartTimeText = findViewById(R.id.textView12);
        this.EndTimeText = findViewById(R.id.textView13);
        this.DateText = findViewById(R.id.textView27);

        DbConnect dbConnect = new DbConnect(this);
        SQLiteDatabase sqLiteDatabase = dbConnect.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Date FROM Class",null);

        while (cursor.moveToNext()){
            String Date = cursor.getString(cursor.getColumnIndex("Date"));

            String[] DateP = Date.split("/");
            calendarView.markDate(Integer.parseInt(DateP[0]), Integer.parseInt(DateP[1]), Integer.parseInt(DateP[2]));
        }
        sqLiteDatabase.close();

        calendarView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                DbConnect dbConnect = new DbConnect(view.getContext());
                SQLiteDatabase sqLiteDatabase = dbConnect.getReadableDatabase();
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Class WHERE Date ="+"'"+ date.getYear()+"/"+date.getMonth()+"/"+date.getDay()+"'",null);
                System.out.println(date.getDay());
                while (cursor.moveToNext()){
                    String Title = cursor.getString(cursor.getColumnIndex("SubjectName"));
                    SubjectText.setText(Title);
                    String Location = cursor.getString(cursor.getColumnIndex("Location"));
                    LocationText.setText(Location);
                    String Classroom = cursor.getString(cursor.getColumnIndex("Classroom"));
                    ClassroomText.setText(Classroom);
                    String StartTime = cursor.getString(cursor.getColumnIndex("StartTime"));
                    StartTimeText.setText(StartTime);
                    String EndTime = cursor.getString(cursor.getColumnIndex("EndTime"));
                    EndTimeText.setText(EndTime);
                    String Date = cursor.getString(cursor.getColumnIndex("Date"));
                    DateText.setText(Date);
                }
            }
        });

    }
}