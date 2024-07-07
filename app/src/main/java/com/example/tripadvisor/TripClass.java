package com.example.tripadvisor;

 public class TripClass {
 private String Location;
 private String From;
 private String PointA;
 private String PointB;
 private String Description;
 private int price;
 private int Days;
 private int imageResId;

     public TripClass() {
     }

     public TripClass(int imageResId,String location, String from, String pointA, String pointB, String description, int price, int days) {
         Location = location;
         From = from;
         PointA = pointA;
         PointB = pointB;
         Description = description;
         this.price = price;
         Days = days;
         this.imageResId = imageResId;
     }

     public String getLocation() {
         return Location;
     }

     public void setLocation(String location) {
         Location = location;
     }

     public String getFrom() {
         return From;
     }

     public void setFrom(String from) {
         From = from;
     }

     public String getPointA() {
         return PointA;
     }

     public void setPointA(String pointA) {
         PointA = pointA;
     }

     public String getPointB() {
         return PointB;
     }

     public void setPointB(String pointB) {
         PointB = pointB;
     }

     public String getDescription() {
         return Description;
     }

     public void setDescription(String description) {
         Description = description;
     }

     public int getPrice() {
         return price;
     }

     public void setPrice(int price) {
         this.price = price;
     }

     public int getDays() {
         return Days;
     }

     public void setDays(int days) {
         Days = days;
     }

     public int getImageResId() {
         return imageResId;
     }

     public void setImageResId(int imageResId) {
         this.imageResId = imageResId;
     }
 }

