package com.example.to_do.feature_to_do.data.di

import android.content.Context
import androidx.room.Room
import com.example.to_do.feature_to_do.data.local.ToDoDao
import com.example.to_do.feature_to_do.data.local.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToDoModule {
    @Provides
    fun providesRoomDao(database: ToDoDatabase): ToDoDao {
        return database.dao
    }

    @Provides
    @Singleton
    fun providesRoomDb(
        @ApplicationContext appContext: Context
    ): ToDoDatabase{
        return Room.databaseBuilder(
            appContext.applicationContext,
            ToDoDatabase::class.java,
            "todo_db"
        ).fallbackToDestructiveMigrationFrom().build()
    }
}