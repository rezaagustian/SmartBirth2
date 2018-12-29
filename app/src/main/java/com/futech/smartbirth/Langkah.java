package com.futech.smartbirth;

public class Langkah {
    private String langkah;
    private String tanggal;

    public Langkah(String langkah, String tanggal){
        this.langkah = langkah;
        this.tanggal = tanggal;
    }

    public String getLangkah() {
        return langkah;
    }

    public String getTanggal() {
        return tanggal;
    }
}
