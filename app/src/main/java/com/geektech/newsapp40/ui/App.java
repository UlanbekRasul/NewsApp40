package com.geektech.newsapp40.ui;

import android.app.Application;

import androidx.room.Room;

import com.geektech.newsapp40.data.AppDatabase;

public class App extends Application {

    public static App instance;
    public static AppDatabase database;

    public static App getInstance() {
        return instance;
    }

    public static AppDatabase getDatabase() {
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "news-db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }
}