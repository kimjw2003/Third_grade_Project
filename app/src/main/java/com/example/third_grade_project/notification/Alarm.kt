package com.example.third_grade_project.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ContentProvider
import android.content.Context
import android.content.Intent
import com.example.third_grade_project.notification.NotificationManager.MakeNotification
import com.example.third_grade_project.view.fragment.HomeFragment.Companion.isAlarm
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContentProviderCompat
import com.example.third_grade_project.R
import com.example.third_grade_project.view.MainActivity

class Alarm :BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent) {
        Log.d("Logd", "alarm on")

        if(isAlarm){
            Log.d("Logd", "isAlarm is $isAlarm")
            MakeNotification(p0, "일기장 알림", "오늘 일기를 작성하지 않으셨어요!")
        } else{
            Log.d("Logd", "isAlarm is $isAlarm")
            isAlarm = true
        }
    }
}