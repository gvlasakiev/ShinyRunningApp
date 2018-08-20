package com.example.jorexa.shinyrunnigapp.models;

import java.util.Date;

public class Workout {
    public String name;
    public String distance;
    public String time;
    public Date date;

    public Workout() {

    }

    public Workout(String name, String distance, String time) {
        this.name = name;
        this.distance = distance;
        this.time = time;
        this.date = new Date();
    }
}
