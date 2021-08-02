package kim.jong.third_grade_project.notification


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kim.jong.third_grade_project.notification.NotificationManager.MakeNotification
import kim.jong.third_grade_project.view.fragment.HomeFragment.Companion.isAlarm
import android.util.Log

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