package kim.jong.third_grade_project.module

import kim.jong.third_grade_project.db.DiaryDao
import kim.jong.third_grade_project.db.DiaryRepository
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
    fun provideRepository(diaryDao: DiaryDao) : DiaryRepository{
        return DiaryRepository(diaryDao)
    }
}