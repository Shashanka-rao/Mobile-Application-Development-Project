package com.example.miniprojectmad1.Models;

public class BookServiceModel {
    private String location;
    private String selectedDate;
    private String selectedTime;
    private String pickUpLoc;
    private String dropLoc;

    public BookServiceModel() {
    }

    public BookServiceModel(String location, String selectedDate, String selectedTime, String pickUpLoc, String dropLoc) {
        this.location = location;
        this.selectedDate = selectedDate;
        this.selectedTime = selectedTime;
        this.pickUpLoc = pickUpLoc;
        this.dropLoc = dropLoc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime) {
        this.selectedTime = selectedTime;
    }

    public String getPickUpLoc() {
        return pickUpLoc;
    }

    public void setPickUpLoc(String pickUpLoc) {
        this.pickUpLoc = pickUpLoc;
    }

    public String getDropLoc() {
        return dropLoc;
    }

    public void setDropLoc(String dropLoc) {
        this.dropLoc = dropLoc;
    }
}
