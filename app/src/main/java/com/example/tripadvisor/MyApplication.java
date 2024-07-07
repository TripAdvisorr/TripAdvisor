package com.example.tripadvisor;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    public static ArrayList<Videos> videos;
    public static ArrayList<TripClass> Trips;

    @Override
    public void onCreate() {
        super.onCreate();
        videos = new ArrayList<>();
        Trips = new ArrayList<>();

        videos.add(new Videos(R.raw.v1));
        videos.add(new Videos(R.raw.v2));
        videos.add(new Videos(R.raw.v3));
        videos.add(new Videos(R.raw.v1));


        Trips.add(new TripClass(R.drawable.islamabad, " Trip To Islamabad","Lahore","Pindi","Islamabad","We go from lahore to Islamabad",15000,4));
        Trips.add(new TripClass(R.drawable.lahore1,  " Trip To Lahore","Islamabad","Pindi","Lahore","We go from Islamabad to Lahore",14500,3));
        Trips.add(new TripClass(R.drawable.peshawar,  " Trip To Peshawar","Lahore","Islamabad","Peshawar","We go from lahore to Peshawar",22000,4));
        Trips.add(new TripClass(R.drawable.sakrdu,  " Trip To Skardu","Lahore","Islamabad","Skardu","We go from lahore to Skardu",25000,3));
        Trips.add(new TripClass(R.drawable.saifulmaluk,  " Trip To Lake SaifulMalook","Islamabad","Naran Kaghan","SaifulMalook","We go from Islamabad to Lake SaifulMalook",30000,4));
        Trips.add(new TripClass(R.drawable.naran,  " Trip To Naran Kaghan","Islamabad","Murree","Naran ","We go from Islamabad to Naran Kaghan",24000,5));
        Trips.add(new TripClass(R.drawable.ruhtasfort,  " Trip To Ruhtas Fort","Lahore","Multan","Ruhtas Fort","We go from lahore to Ruhtas Fort",15000,2));
        Trips.add(new TripClass(R.drawable.siachen,  " Trip To Siachen ","Multan","Islamabad","Siachen","We go from Multan to Siachen",35000,6));
        Trips.add(new TripClass(R.drawable.fairymeadows,  " Trip To Fairy Meadows","Lahore","Pindi","Fairy Meadows","We go from lahore to Fairy Meadows",35000,5));
    }
}
