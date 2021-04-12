package com.example.third_grade_project.db

class DiaryRepository(private val dao : DiaryDao) {

    suspend fun getAllDiary() =
        dao.getAllDiary()

    suspend fun insert(diary: Diary) =
        dao.insertDiary(diary)

    suspend fun delete(diary: Diary) =
        dao.deleteDiary(diary)

    suspend fun getAllDateDiary(date: String) =
        dao.getAllDiary(date)

    fun getAllDiaryFlow() = dao.getAllDiaryFlow()
}