package com.example.third_grade_project.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.ActivityMainBinding
import com.example.third_grade_project.notification.NotificationCreator
import com.example.third_grade_project.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val channelId = "com.example.third_grade_project"
    private var notificationManager : NotificationManager? = null

    private val currentDateTime = Calendar.getInstance().time
    private var nowDate = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(currentDateTime)

    private lateinit var binding : ActivityMainBinding
    lateinit var homeviewModel : HomeViewModel

    private var mBackWait: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        homeviewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // navController
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_home, R.id.navigation_list, R.id.navigation_calender, R.id.navigation_settings)
        )
        binding.navView.setupWithNavController(navController)

        // notification settings
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelId, "Mood Diary", "오늘 일기를 작성하지 않으셨어요!")


        homeviewModel.diaryFlow.observe(this, Observer {
            if(it.any{ diary -> nowDate == diary.date }){
                Log.d("Logd", "written")
            }else{
                Log.d("Logd", "no written")
                displayNotification()
            }
        })
    }


        fun setAlarm(context: Context){
            NotificationCreator.register(context, 21, 0)
        }

    private fun displayNotification(){
        val notificationId = 45
        val tapResultIntent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this@MainActivity, channelId)
                .setContentTitle("일기장 알림")
                .setContentText("오늘 일기를 작성하지 않으셨어요!")
                .setSmallIcon(R.drawable.diary_logo)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id : String, name : String, channelDescription : String){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - mBackWait >= 1500) {
            mBackWait = System.currentTimeMillis()
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show()
        } else {
            moveTaskToBack(true)
            finish()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}