package com.example.miniprojectmad1.Models;

public class ServiceCostModel extends ServiceHistoryModel {
    String kmsIn;
    public ServiceCostModel()
    { }
    public ServiceCostModel(String kmsIn)
    {
     this.kmsIn = kmsIn;
    }

    public String getKmsIn() {
        return kmsIn;
    }

    public void setKmsIn(String kmsIn) {
        this.kmsIn = kmsIn;
    }
}
