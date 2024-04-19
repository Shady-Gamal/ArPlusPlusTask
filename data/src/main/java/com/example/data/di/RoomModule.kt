package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.data_sources.local.ArPlusPlusDatabase
import com.example.data.data_sources.local.Converters
import com.example.data.util.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "ArPlusPlus_Database"

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context : Context) : ArPlusPlusDatabase{

        return Room.databaseBuilder(context, ArPlusPlusDatabase::class.java , DATABASE_NAME)
            .addTypeConverter(Converters(GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
    }
}