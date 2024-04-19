package com.example.data.data_sources.local.databaseDAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.ArticlesItemModel

@Dao
interface NewsDao {

    @Query("SELECT * FROM ArticlesItemModel")
    suspend fun getNews(): List<ArticlesItemModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsItem: ArticlesItemModel)

    @Delete
    suspend fun deleteNews(newsItem: ArticlesItemModel)
}