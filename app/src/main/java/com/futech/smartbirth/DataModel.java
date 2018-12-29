package com.futech.smartbirth;

public class DataModel {


    private String tanggal;
    private String berat;
    private String langkah;



    public DataModel(String tanggal, String berat, String langkah){
        this.tanggal = tanggal;
        this.berat = berat;
        this.langkah = langkah;

    }


    public String getTanggal() {
        return tanggal;
    }

    public String getBerat() {
        return berat;
    }

    public String getLangkah() {
        return langkah;
    }

}