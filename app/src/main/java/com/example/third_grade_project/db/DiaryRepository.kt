package com.example.third_grade_project.db

class DiaryRepository(private val dao : DiaryDao) {

    val diary = dao.getAllDiary()

    suspend fun insert(diary: Diary){
        dao.insertDiary(diary)
    }

    suspend fun delete(diary: Diary){
        dao.deleteDiary(diary)
    }
}