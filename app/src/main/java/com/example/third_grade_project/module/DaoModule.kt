package com.example.third_grade_project.module

import com.example.third_grade_project.db.DiaryDao
import com.example.third_grade_project.db.DiaryDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DaoModule {

    @Singleton
    @Provides
    fun getDao(diaryDb: DiaryDb) : DiaryDao{
        return diaryDb.diaryDao
    }
}