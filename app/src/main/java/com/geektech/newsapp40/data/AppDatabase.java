package com.geektech.newsapp40.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;


import models.News;
@Database(entities = {News.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private AppDatabase database;

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public abstract NewsDao newsDao();
}