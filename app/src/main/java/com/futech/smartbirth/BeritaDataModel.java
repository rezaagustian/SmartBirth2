package com.futech.smartbirth;

public class BeritaDataModel {

    private String judulBerita;
    private String tanggalBerita;
    private String tanggalBerita2;
    private String deskripsiBerita;

    public BeritaDataModel(){

    }

    public BeritaDataModel(String judulBerita, String deskripsiBerita, String tanggalBerita, String tanggalBerita2){
        this.judulBerita = judulBerita;
        this.deskripsiBerita = deskripsiBerita;
        this.tanggalBerita = tanggalBerita;
        this.tanggalBerita2 = tanggalBerita2;
    }

    public String getJudulBerita() {
        return judulBerita;
    }


    public String getDeskripsiBerita() {
        return deskripsiBerita;
    }

    public String getTanggalBerita() {
        return tanggalBerita;
    }

    public String getTanggalBerita2() {
        return tanggalBerita2;
    }

}
