package com.example.data.data_sources.local

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.data.data_sources.local.databaseDAO.NewsDao
import com.example.data.models.ArticlesItemModel

@Database(entities = [ArticlesItemModel::class],
    version = 3)
@TypeConverters(Converters::class)
abstract class ArPlusPlusDatabase : RoomDatabase() {
    abstract fun getNewsDao() : NewsDao
}