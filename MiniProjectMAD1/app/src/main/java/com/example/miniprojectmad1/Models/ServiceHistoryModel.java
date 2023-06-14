package com.example.miniprojectmad1.Models;

public class ServiceHistoryModel {
    String kmsIn,dateIn,billIn;

    public ServiceHistoryModel()
    { }

    public ServiceHistoryModel(String kmsIn)
    {
        this.kmsIn = kmsIn;
    }
    public ServiceHistoryModel(String kmsIn, String dateIn, String billIn) {
        this.kmsIn = kmsIn;
        this.dateIn = dateIn;
        this.billIn = billIn;
    }

    public String getKmsIn() {
        return kmsIn;
    }

    public void setKmsIn(String kmsIn) {
        this.kmsIn = kmsIn;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getBillIn() {
        return billIn;
    }

    public void setBillIn(String billIn) {
        this.billIn = billIn;
    }
}
