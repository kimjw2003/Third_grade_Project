package kim.jong.third_grade_project

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {
    companion object{
        lateinit var prefs : kim.jong.third_grade_project.PreferenceUtil
    }

    override fun onCreate() {
        kim.jong.third_grade_project.Application.Companion.prefs =
            kim.jong.third_grade_project.PreferenceUtil(applicationContext)
        super.onCreate()
    }
}