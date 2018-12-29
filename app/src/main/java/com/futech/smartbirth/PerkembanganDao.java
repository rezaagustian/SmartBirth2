package com.futech.smartbirth;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PerkembanganDao {

    @Query("SELECT * from perkembangan_table ORDER BY uid ASC")
    LiveData<List<Data>> getAllData();

    @Query("SELECT * FROM perkembangan_table where uid = :id_ibu")
    Data findByUid(String id_ibu);

    @Query("DELETE FROM perkembangan_table")
    void deleteAll();

    @Insert
    void insert(Data data);

    @Delete
    void delete(Data data);
}