package com.example.classschedule;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.SweepGradient;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.Locale;

public class addSubject_activity extends AppCompatActivity {
    Toolbar toolbar2;
    EditText Subject, Location, Classroom;
    Button StartTime, EndTime, Mon, Tue, Wed, Thu, Fri, Sat, Sun, Save;
    String Day, TimeStart, TimeEnd, Select_Date;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
//        Day = " ";
        super.onCreate(savedInstanceState);
//        this.toolbar2 = (Toolbar)findViewById(R.id.toolbar2);

        setContentView(R.layout.activity_add_subject_activity);
        this.Subject = findViewById(R.id.editTextTextPersonName);
        this.Location = findViewById(R.id.editTextTextPersonName2);
        this.Classroom = findViewById(R.id.editTextTextPersonName3);
        this.StartTime = findViewById(R.id.button);
        this.EndTime = findViewById(R.id.button2);
        this.Mon = findViewById(R.id.button3);
        this.Tue = findViewById(R.id.button4);
        this.Wed = findViewById(R.id.button5);
        this.Thu = findViewById(R.id.button6);
        this.Fri = findViewById(R.id.button7);
        this.Sat = findViewById(R.id.button8);
        this.Sun = findViewById(R.id.button9);
        this.Save = findViewById(R.id.button11);

//        setSupportActionBar(toolbar2);
//        getSupportActionBar().setTitle("dsfsdf");


    }

    public void Date_Select(View view) {
        Calendar calendar;
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(addSubject_activity.this, new DatePickerDialog.OnDateSetListener() {
            //            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                //แปลงวันออกมาเป็นตัวย่อ
//                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH);
//                LocalDate localDate = LocalDate.of(i,i1,i2);
//                Day = localDate.format(dateTimeFormatter); //ตัวย่อ 3 ตัว

                Select_Date = i + "/" + (i1 + 1) + "/" + i2;
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    public void Day(View view) {
        if (view.getId() == R.id.button3) {
            Day = "Monday";
        } else if (view.getId() == R.id.button4) {
            Day = "Tuesday";
        } else if (view.getId() == R.id.button5) {
            Day = "Wednesday";
        } else if (view.getId() == R.id.button6) {
            Day = "Thursday";
        } else if (view.getId() == R.id.button7) {
            Day = "Friday";
        } else if (view.getId() == R.id.button8) {
            Day = "Saturday";
        } else if (view.getId() == R.id.button9) {
            Day = "Sunday";
        }
        Toast.makeText(this, Day, Toast.LENGTH_LONG).show();
    }

    public void TimeStart(View view) {
        Calendar calendar;
        calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(addSubject_activity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (minute < 10) {
                    TimeStart = hourOfDay + ":0" + minute;
                } else {
                    TimeStart = hourOfDay + ":" + minute;
                }

            }
        }, hours, minute, false);
        timePickerDialog.show();

    }

    public void TimeEnd(View view) {
        Calendar calendar;
        calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(addSubject_activity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (minute < 10) {
                    TimeEnd = hourOfDay + ":0" + minute;
                } else {
                    TimeEnd = hourOfDay + ":" + minute;
                }
            }
        }, hours, minute, false);
        timePickerDialog.show();

    }

    public void Save(View view) {
//        if (Subject != null ){
//            Toast.makeText(this,  "กรุณาใส่วิชาที่เรียน" , Toast.LENGTH_LONG).show();
//        }else if (Subject == null && Location!= null){
//            Toast.makeText(this,  "กรุณาใส่สถานที่เรียน" , Toast.LENGTH_LONG).show();
//        }else if (Classroom != null){
//            Toast.makeText(this,  "กรุณาใส่ห้องเรียน" , Toast.LENGTH_LONG).show();
//        }else if (StartTime != null){
//            Toast.makeText(this,  "กรุณาใส่เวลาเริ่มเรียน" , Toast.LENGTH_LONG).show();
//        }else if (EndTime != null){
//            Toast.makeText(this,  "กรุณาใส่เวลาเลิกเรียน" , Toast.LENGTH_LONG).show();
//        }else if (EndTime != null){
//            Toast.makeText(this,  "กรุณากดปุ่มเลือกวันเรียน" , Toast.LENGTH_LONG).show();
//        }else{
        if (Select_Date != null) {
            DbConnect dbConnect = new DbConnect(this);
            SQLiteDatabase sqLiteDatabase = dbConnect.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("SubjectName", Subject.getText().toString());
            contentValues.put("Location", Location.getText().toString());
            contentValues.put("Classroom", Classroom.getText().toString());
            contentValues.put("StartTime", TimeStart);
            contentValues.put("EndTime", TimeEnd);
            contentValues.put("Day", Day);
            contentValues.put("Date", Select_Date);
            sqLiteDatabase.insert("Class", null, contentValues);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{

            Toast.makeText(this, "กรุณาเลือกวันที่เรียน", Toast.LENGTH_LONG).show();
        }
    }

}







