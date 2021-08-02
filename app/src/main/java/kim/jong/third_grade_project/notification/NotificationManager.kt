package kim.jong.third_grade_project.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import kim.jong.third_grade_project.R
import kim.jong.third_grade_project.view.MainActivity
import java.util.*

object NotificationManager {
    const val NOTIFICATION_CHANNEL_ID = "10001"
    fun MakeNotification(context : Context, title : String, content : String){
        NotificationSomething(context, title, content)
    }
    fun NotificationSomething(context: Context, title: String, content: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_foreground)) //BitMap 이미지 요구
                .setContentTitle(title)
                .setContentText(content) // 더 많은 내용이라서 일부만 보여줘야 하는 경우 아래 주석을 제거하면 setContentText에 있는 문자열 대신 아래 문자열을 보여줌
                .setSmallIcon(R.drawable.diary_logo)
                .setStyle(NotificationCompat.BigTextStyle().bigText(content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent) // 사용자가 노티피케이션을 탭시 MainActivity로 이동하도록 설정
                .setAutoCancel(true)

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setSmallIcon(R.drawable.ic_launcher_foreground) //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            val channelName: CharSequence = "노티페케이션 채널"
            val description = "오레오 이상을 위한 것임"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            channel.description = description
            notificationManager.createNotificationChannel(channel)
        } else builder.setSmallIcon(R.mipmap.ic_launcher) // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남
        notificationManager.notify(1234, builder.build()) // 고유숫자로 노티피케이션 동작시킴
    }
}