package com.futech.smartbirth;

public class User {

    private String nik;
    private String nama;

    private String tl;
    private String latitude;
    private String longitude;
    private String telp;
    private String alamat;
    private String dusun;
    private String zoom;


    //login
    public User(String nik, String nama) {
        this.nik = nik;
        this.nama = nama;
    }


    public User(String nik, String latitude, String longitude, String zoom) {
        this.nik = nik;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zoom = zoom;
    }

    public User(String nik, String nama, String dusun, String alamat, String tl, String telp) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.telp = telp;
        this.dusun = dusun;
        this.tl = tl;
    }


    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }

    public String getTelp() {
        return telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTL() {
        return tl;
    }

    public String getDusun() {
        return dusun;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
