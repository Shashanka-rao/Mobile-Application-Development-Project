package com.example.miniprojectmad1.Models;

public class BookServiceModel {
    String location,selectedDate,selectedTime;

    public BookServiceModel()
    { }

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

    public BookServiceModel(String location, String selectedDate, String selectedTime)
    {
        this.location=location;
        this.selectedDate=selectedDate;
        this.selectedTime=selectedTime;
    }
}
