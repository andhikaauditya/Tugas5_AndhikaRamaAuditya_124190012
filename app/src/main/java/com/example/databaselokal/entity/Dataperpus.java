package com.example.databaselokal.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "perpus_db")

public class Dataperpus {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "namainstusi")
    private String namainstusi;
    @ColumnInfo(name = "namapengunjung")
    private String namapengunjung;
    @ColumnInfo(name = "alamat")
    private String alamat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamainstusi() {
        return namainstusi;
    }

    public void setNamainstusi(String namainstusi) {
        this.namainstusi = namainstusi;
    }

    public String getNamapengunjung() {
        return namapengunjung;
    }

    public void setNamapengunjung(String namapengunjung) {
        this.namapengunjung = namapengunjung;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
