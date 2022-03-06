package com.geektech.newsapp40.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import models.News;

@Database(entities = {News.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}