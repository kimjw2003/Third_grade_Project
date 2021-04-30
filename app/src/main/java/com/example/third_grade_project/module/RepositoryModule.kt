package com.example.third_grade_project.module

import com.example.third_grade_project.db.DiaryDao
import com.example.third_grade_project.db.DiaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun getRepository(diaryDao: DiaryDao) : DiaryRepository{
        return DiaryRepository(diaryDao)
    }
}