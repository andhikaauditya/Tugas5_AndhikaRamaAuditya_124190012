package com.example.databaselokal.view;


import android.view.View;

import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.Dataperpus;

import java.util.List;

public interface MainContact {
    //kodingan activuty
    interface  view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<Dataperpus> list);
        void editData(Dataperpus item);
        void deleteData(Dataperpus item);

    }
    //kodingan untuk database
    interface presenter{
        void insertData(String namainstansi, String namapengunjung, String alamat , AppDatabase database);
        void readData(AppDatabase database);
        void editData(String namainstansi, String namapengunjung, String alamat , int id, AppDatabase database);
        void deleteData(Dataperpus dataperpus, AppDatabase database);

    }
}
