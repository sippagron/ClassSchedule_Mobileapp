package com.example.classschedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.opengl.ETC1;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private ArrayList<Class> classArrayList;
    Context context;

    public ClassAdapter(Context context,ArrayList<Class>classArrayList) {
        this.context = context;
        this.classArrayList = classArrayList;
        //System.out.println("X");
    }


    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.subjectform,parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ViewHolder holder, int position) {
        holder.SubjectName.setText(classArrayList.get(position).getSubjectName());
        holder.Classroom.setText(classArrayList.get(position).getClassroom());
        holder.Location.setText(classArrayList.get(position).getLocation());
        holder.StartTime.setText(classArrayList.get(position).getStartTime());
        holder.EndTime.setText(classArrayList.get(position).getEndTime());
        holder.Day.setText(classArrayList.get(position).getDay());
        if (holder.Day.getText().equals("Monday")){
            holder.Del.setBackgroundColor(Color.parseColor("#FBC02D"));
        }else if (holder.Day.getText().equals("Tuesday")){
            holder.Del.setBackgroundColor(Color.parseColor("#EC407A"));
        }else if (holder.Day.getText().equals("Wednesday")){
            holder.Del.setBackgroundColor(Color.parseColor("#388E3C"));
        }else if (holder.Day.getText().equals("Thursday")){
            holder.Del.setBackgroundColor(Color.parseColor("#F4511E"));
        }else if (holder.Day.getText().equals("Friday")){
            holder.Del.setBackgroundColor(Color.parseColor("#1E88E5"));
        }else if (holder.Day.getText().equals("Saturday")){
            holder.Del.setBackgroundColor(Color.parseColor("#8E24AA"));
        }else if (holder.Day.getText().equals("Sunday")){
            holder.Del.setBackgroundColor(Color.parseColor("#E81414"));
        }


    }

    @Override
    public int getItemCount() {

        return classArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView SubjectName,Classroom,Location,StartTime,EndTime,Day,Date;
        Button Del;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SubjectName = itemView.findViewById(R.id.textView15);
            Classroom = itemView.findViewById(R.id.textView19);
            Location = itemView.findViewById(R.id.textView17);
            StartTime = itemView.findViewById(R.id.textView21);
            EndTime = itemView.findViewById(R.id.textView23);
            Day = itemView.findViewById(R.id.textView25);
            Del = itemView.findViewById(R.id.button10);

           


            //method del
            Del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DbConnect dbConnect = new DbConnect(context);
                    SQLiteDatabase sqLiteDatabase = dbConnect.getWritableDatabase();
                    sqLiteDatabase.execSQL("DELETE FROM Class WHERE SubjectID ="+classArrayList.get(getAdapterPosition()).getSubjectID());
                    Intent intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context,  "ลบตารางเรียนเสร็จสิ้น" , Toast.LENGTH_LONG).show();
                }
            });

        }
    }


}
