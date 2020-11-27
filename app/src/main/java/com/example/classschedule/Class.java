package com.example.classschedule;

public class Class {
    private String SubjectName, Location, Classroom, StartTime, EndTime,Day,Date;

    private int SubjectID;

    public Class(String subjectName, String location, String classroom, String startTime, String endTime, int subjectID,String Day,String Date) {
        SubjectName = subjectName;
        Location = location;
        Classroom = classroom;
        StartTime = startTime;
        EndTime = endTime;
        SubjectID = subjectID;
        this.Day = Day;
        this.Date = Date;
    }

    public String getDate() {
        return Date;
    }

    public String getDay() {
        return Day;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getLocation() {
        return Location;
    }

    public String getClassroom() {
        return Classroom;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public int getSubjectID() {
        return SubjectID;
    }
}
