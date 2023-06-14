package com.example.miniprojectmad1.Models;

public class UserCreationModel {
    String username,mobileNo,vRegNo,email;

    public UserCreationModel() {
        this.username = username;
        this.mobileNo = mobileNo;
        this.vRegNo = vRegNo;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getvRegNo() {
        return vRegNo;
    }

    public void setvRegNo(String vRegNo) {
        this.vRegNo = vRegNo;
    }
}
