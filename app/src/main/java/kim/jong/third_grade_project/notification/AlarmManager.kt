package kim.jong.third_grade_project.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

object AlarmManager {

        lateinit var alarmManager: AlarmManager

        fun register(context : Context, hour : Int, minute : Int, requestCode : Int) {
            alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val intent = Intent(context, Alarm::class.java).let {
                it.putExtra("requestcode" , requestCode)
                PendingIntent.getBroadcast(context, requestCode, it, 0)
            }
            val calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
            }
            Log.d("TAG", "register: ${calendar.timeInMillis}")
            alarmManager.cancel(intent)
            // 지정한 시간에 매일 알림
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY,intent)
        } // regist()..,

        fun unregister(context: Context, requestCode : Int) {
            alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, Alarm::class.java)
            val pIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
            alarmManager.cancel(pIntent)
        } // unregist()..
    }