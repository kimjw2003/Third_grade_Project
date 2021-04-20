package com.example.third_grade_project.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {

    @Insert
    suspend fun insertDiary(diary: Diary) : Long

    @Delete
    suspend fun deleteDiary(diary: Diary)

    @Query("SELECT * FROM diary_data_table")
    suspend fun getAllDiary() : List<Diary>

    @Query("SELECT * FROM diary_data_table")
    fun getAllDiaryFlow() : Flow<List<Diary>>

    @Query("SELECT * FROM diary_data_table where diary_date = :date")
    suspend fun getAllDateDiary(date: String) : List<Diary>
}