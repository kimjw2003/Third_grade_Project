package kim.jong.third_grade_project.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kim.jong.third_grade_project.model.Diary

@Database(entities = [Diary::class], version = 3, exportSchema = false)
abstract class DiaryDb : RoomDatabase() {
    abstract val diaryDao : DiaryDao

}