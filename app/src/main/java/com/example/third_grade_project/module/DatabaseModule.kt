package com.example.third_grade_project.module

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
    fun getDatabase(context: Context) : DiaryDb {
        return Room.databaseBuilder(context, DiaryDb::class.java, "diary_data_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}