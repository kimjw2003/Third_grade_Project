package com.example.third_grade_project.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Diary::class], version = 3)
abstract class DiaryDb : RoomDatabase() {
    abstract val diaryDao : DiaryDao

    companion object{
        @Volatile
        private var INSTANCE : DiaryDb? = null
        fun getInstance(context: Context):DiaryDb{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            DiaryDb::class.java, "diary_data_database")
                            .fallbackToDestructiveMigration()
                            .build()
                }
                return instance
            }
        }
    }
}