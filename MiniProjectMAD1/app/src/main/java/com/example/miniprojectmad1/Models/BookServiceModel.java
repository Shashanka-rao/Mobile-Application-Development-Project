package com.example.miniprojectmad1.Models;

public class BookServiceModel {
    private String location;
    private String selectedDate;
    private String selectedTime;
    private String pickUpLoc;
    private String dropLoc;
    private String UserIdBook;
    private String userNameB;
    private String vRegNoB;

    public BookServiceModel() {
    }

    public BookServiceModel(String location, String selectedDate, String selectedTime, String pickUpLoc, String dropLoc, String userIdBook, String userNameB, String vRegNoB) {
        this.location = location;
        this.selectedDate = selectedDate;
        this.selectedTime = selectedTime;
        this.pickUpLoc = pickUpLoc;
        this.dropLoc = dropLoc;
        this.UserIdBook = userIdBook;
        this.userNameB = userNameB;
        this.vRegNoB = vRegNoB;
    }

    public String getUserNameB() {
        return userNameB;
    }

    public void setUserNameB(String userNameB) {
        this.userNameB = userNameB;
    }

    public String getvRegNoB() {
        return vRegNoB;
    }

    public void setvRegNoB(String vRegNoB) {
        this.vRegNoB = vRegNoB;
    }

    public String getUserIdBook() {
        return UserIdBook;
    }

    public void setUserIdBook(String userIdBook) {
        UserIdBook = userIdBook;
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
