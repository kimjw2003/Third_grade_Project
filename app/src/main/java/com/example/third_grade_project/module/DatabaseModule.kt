package com.example.third_grade_project.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.third_grade_project.db.DiaryDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) : DiaryDb {
        return Room.databaseBuilder(application, DiaryDb::class.java, "diary_data_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}