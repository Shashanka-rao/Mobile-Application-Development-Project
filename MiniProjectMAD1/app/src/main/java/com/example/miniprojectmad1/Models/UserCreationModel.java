package com.example.miniprojectmad1.Models;

public class UserCreationModel {
    String username,mobileNo,vRegNo,UserId;

    public UserCreationModel() {
        this.username = username;
        this.mobileNo = mobileNo;
        this.vRegNo = vRegNo;
        this.UserId = UserId;
    }

    public UserCreationModel(String username, String vRegNo) {
        this.username = username;
        this.vRegNo = vRegNo;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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
