package com.hieuminh.diaryv1.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventModel {
    private String title;
    private String description;
    private int hour;
    private int min;
    private int second;
    private int day;
    private int month;

    public EventModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public EventModel(String title, String description) { // mặc định của firebase khi nhận database
        this.title = title;
        this.description = description;
        this.month = LocalDate.now().getMonthValue();
        this.day = LocalDate.now().getDayOfMonth();
        this.hour = LocalTime.now().getHour();
        this.min = LocalTime.now().getMinute();
        this.second = LocalTime.now().getSecond();
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
