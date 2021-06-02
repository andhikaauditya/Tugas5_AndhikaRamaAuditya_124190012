package com.example.databaselokal.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Dataperpus.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataperpusDAO dao();
    private static  AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(context , AppDatabase.class, "dbperpus" ).allowMainThreadQueries().build();
            return appDatabase;
        }
    }

