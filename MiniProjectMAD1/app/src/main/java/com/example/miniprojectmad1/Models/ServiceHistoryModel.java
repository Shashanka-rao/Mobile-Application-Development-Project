package com.example.miniprojectmad1.Models;

public class ServiceHistoryModel extends UserCreationModel{
    String kmsIn,dateIn,billIn,rsPrice,npPrice,ocPrice,wPrice,lPrice;
    String rsDesp,npDesp,ocDesp,wDesp,lDesp,BTotal;

    public ServiceHistoryModel()
    { }

    public ServiceHistoryModel(String kmsIn, String dateIn, String billIn, String rsPrice, String npPrice, String ocPrice, String wPrice, String lPrice, String rsDesp, String npDesp, String ocDesp, String wDesp, String lDesp, String BTotal) {
        this.kmsIn = kmsIn;
        this.dateIn = dateIn;
        this.billIn = billIn;
        this.rsPrice = rsPrice;
        this.npPrice = npPrice;
        this.ocPrice = ocPrice;
        this.wPrice = wPrice;
        this.lPrice = lPrice;
        this.rsDesp = rsDesp;
        this.npDesp = npDesp;
        this.ocDesp = ocDesp;
        this.wDesp = wDesp;
        this.lDesp = lDesp;
        this.BTotal = BTotal;
    }

    public ServiceHistoryModel(String username, String vRegNo, String kmsIn, String dateIn, String billIn, String rsPrice, String npPrice, String ocPrice, String wPrice, String lPrice, String rsDesp, String npDesp, String ocDesp, String wDesp, String lDesp, String BTotal) {
        super(username, vRegNo);
        this.kmsIn = kmsIn;
        this.dateIn = dateIn;
        this.billIn = billIn;
        this.rsPrice = rsPrice;
        this.npPrice = npPrice;
        this.ocPrice = ocPrice;
        this.wPrice = wPrice;
        this.lPrice = lPrice;
        this.rsDesp = rsDesp;
        this.npDesp = npDesp;
        this.ocDesp = ocDesp;
        this.wDesp = wDesp;
        this.lDesp = lDesp;
        this.BTotal = BTotal;
    }

    public String getBTotal() {
        return BTotal;
    }

    public void setBTotal(String BTotal) {
        this.BTotal = BTotal;
    }

    public String getRsDesp() {
        return rsDesp;
    }

    public void setRsDesp(String rsDesp) {
        this.rsDesp = rsDesp;
    }

    public String getNpDesp() {
        return npDesp;
    }

    public void setNpDesp(String npDesp) {
        this.npDesp = npDesp;
    }

    public String getOcDesp() {
        return ocDesp;
    }

    public void setOcDesp(String ocDesp) {
        this.ocDesp = ocDesp;
    }

    public String getwDesp() {
        return wDesp;
    }

    public void setwDesp(String wDesp) {
        this.wDesp = wDesp;
    }

    public String getlDesp() {
        return lDesp;
    }

    public void setlDesp(String lDesp) {
        this.lDesp = lDesp;
    }

    public String getRsPrice() {
        return rsPrice;
    }

    public void setRsPrice(String rsPrice) {
        this.rsPrice = rsPrice;
    }

    public String getNpPrice() {
        return npPrice;
    }

    public void setNpPrice(String npPrice) {
        this.npPrice = npPrice;
    }

    public String getOcPrice() {
        return ocPrice;
    }

    public void setOcPrice(String ocPrice) {
        this.ocPrice = ocPrice;
    }

    public String getwPrice() {
        return wPrice;
    }

    public void setwPrice(String wPrice) {
        this.wPrice = wPrice;
    }

    public String getlPrice() {
        return lPrice;
    }

    public void setlPrice(String lPrice) {
        this.lPrice = lPrice;
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
