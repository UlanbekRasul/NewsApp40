package com.geektech.newsapp40.ui;

import android.app.Application;

import androidx.room.Room;

import com.geektech.newsapp40.data.AppDatabase;

public class App extends Application {

    public static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "news-db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }
}