package com.geektech.newsapp40.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import models.News;

@Dao
public interface NewsDao {

    @Insert
    void insetNews(News news);

    @Query("SELECT * FROM news ORDER by id DESC")
    LiveData<List<News>> getAllNews();

    @Delete
    void deleteNews(News news);

}