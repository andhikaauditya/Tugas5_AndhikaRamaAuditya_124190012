package com.example.databaselokal.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataperpusDAO {
    @Insert
    Long insertData(Dataperpus dataperpus);

    @Query("Select * from perpus_db")
    List<Dataperpus> getData();

    @Update
    int updateData(Dataperpus item);

    @Delete
    void deleteData(Dataperpus item);
}
