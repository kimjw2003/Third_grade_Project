package com.example.third_grade_project.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.third_grade_project.view.MainActivity
import java.util.*

object NotificationCreator {
    lateinit var alarmManager: AlarmManager

    fun register(context : Context, hour : Int, minute : Int) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val tapResultIntent = Intent(context, MainActivity::class.java)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        Log.d("TAG", "register: ${calendar.timeInMillis}")

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, PendingIntent.getActivity(context, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT))
    }
}