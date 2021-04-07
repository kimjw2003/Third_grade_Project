package com.example.third_grade_project.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DiaryDao {

    @Insert
    suspend fun insertDiary(diary: Diary) : Long

    @Delete
    suspend fun deleteDiary(diary: Diary)

    @Query("SELECT * FROM diary_data_table")

    fun getAllDiary() : LiveData<List<Diary>>
}