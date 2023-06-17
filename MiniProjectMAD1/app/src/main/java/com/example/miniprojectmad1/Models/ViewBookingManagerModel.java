package com.example.miniprojectmad1.Models;

public class ViewBookingManagerModel extends BookServiceModel {
    String location,dropLoc,pickUpLoc,selectedDate,selectedTime;



    public ViewBookingManagerModel() {
    }

    public ViewBookingManagerModel(String location, String dropLoc, String pickUpLoc, String selectedDate, String selectedTime) {
        this.location = location;
        this.dropLoc = dropLoc;
        this.pickUpLoc = pickUpLoc;
        this.selectedDate = selectedDate;
        this.selectedTime = selectedTime;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDropLoc() {
        return dropLoc;
    }

    public void setDropLoc(String dropLoc) {
        this.dropLoc = dropLoc;
    }

    public String getPickUpLoc() {
        return pickUpLoc;
    }

    public void setPickUpLoc(String pickUpLoc) {
        this.pickUpLoc = pickUpLoc;
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

}
