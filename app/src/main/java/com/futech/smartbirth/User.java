package com.futech.smartbirth;

public class User {

    private String nik, nama;
    private String latitude;
    private String longitide;

    public User(String nik, String nama) {
        this.nik = nik;
        this.nama = nama;
    }

    public User(String latitude, String longitude, String s) {
        this.latitude = latitude;
        this.longitide = longitude;
    }


    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }


    public String getLatitude() {
        return latitude;
    }

    public String getLongitide() {
        return longitide;
    }
}
