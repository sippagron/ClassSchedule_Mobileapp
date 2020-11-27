package com.example.classschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList <Class> classArrayList = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ตารางเรียน");
        setSupportActionBar(toolbar);
        DbConnect dbConnect = new DbConnect(this);
        SQLiteDatabase sqLiteDatabase = dbConnect.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Class",null);

        while (cursor.moveToNext()){
            int ID = cursor.getInt(cursor.getColumnIndex("SubjectID"));
            String Subjectname = cursor.getString(cursor.getColumnIndex("SubjectName"));
            String Location = cursor.getString(cursor.getColumnIndex("Location"));
            String Classroom = cursor.getString(cursor.getColumnIndex("Classroom"));
            String StartTime = cursor.getString(cursor.getColumnIndex("StartTime"));
            String EndTime = cursor.getString(cursor.getColumnIndex("EndTime"));
            String Day = cursor.getString(cursor.getColumnIndex("Day"));
            String Date = cursor.getString(cursor.getColumnIndex("Date"));
            classArrayList.add(new Class(Subjectname,Location,Classroom,StartTime,EndTime,ID,Day,Date));

        }
        sqLiteDatabase.close();

        if (classArrayList.size()>0){
            ClassAdapter classAdapter = new ClassAdapter(this,classArrayList);
            this.recyclerView = findViewById(R.id.recyclerView2);
            recyclerView.setAdapter(classAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.addSubject:
            Intent intent = new Intent(this,addSubject_activity.class);
            startActivity(intent);
            return true;
        case R.id.ShowCalendar:
            Intent intent2 = new Intent(this,CalendarActivity.class);
            startActivity(intent2);
            return true;
        case R.id.clearSubject:
            DbConnect dbConnect = new DbConnect(this);
            SQLiteDatabase sqLiteDatabase = dbConnect.getWritableDatabase();
            sqLiteDatabase.execSQL("DELETE FROM Class");
            Intent intent3 = new Intent(this,MainActivity.class);
            startActivity(intent3);
            Toast.makeText(this,  "ลบตารางเรียนทั้งหมด" , Toast.LENGTH_LONG).show();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}


}