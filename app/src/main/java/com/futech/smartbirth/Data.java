package com.futech.smartbirth;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "perkembangan_table")

public class Data {

    @PrimaryKey(autoGenerate = true)
    private int pid;

    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "berat")
    private Float berat;

    @ColumnInfo(name = "langkah")
    private int langkah;

    @ColumnInfo(name = "tanggal")
    private String tanggal;


    public Data(int langkah){
        this.langkah = langkah;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Float getBerat() {
        return berat;
    }

    public void setBerat(Float berat) {
        this.berat = berat;
    }

    public int getLangkah() {
        return langkah;
    }

    public void setLangkah(int langkah) {
        this.langkah = langkah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}