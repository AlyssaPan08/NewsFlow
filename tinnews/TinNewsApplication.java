package com.laioffer.tinnews;

import android.app.Application;

import androidx.room.Room;

import com.laioffer.tinnews.database.TinNewsDatabase;

//打开app最开始运行的文件. initialize database
//之后再去activity -> fragment

public class TinNewsApplication extends Application {
    private static TinNewsDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, TinNewsDatabase.class, "tinnews_db").build();
    }
    public static TinNewsDatabase getDatabase() {
        return database;
    }
}
