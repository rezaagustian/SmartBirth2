package com.futech.smartbirth;

public class DataModel {


    private String tanggal;
    private String berat;

    public DataModel(String tanggal, String berat){
        this.tanggal = tanggal;
        this.berat = berat;
    }


    public String getTanggal() {
        return tanggal;
    }

    public String getBerat() {
        return berat;
    }

}