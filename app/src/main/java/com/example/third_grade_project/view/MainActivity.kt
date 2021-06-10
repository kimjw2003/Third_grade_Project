package com.example.third_grade_project.view


import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.ActivityMainBinding
import com.example.third_grade_project.notification.AlarmManager
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val currentDateTime = Calendar.getInstance().time
    private var nowDate = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(currentDateTime)

    private lateinit var binding : ActivityMainBinding

    private var mBackWait: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setAlarm(this)

        // navController
        val navController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.navigation_home, R.id.navigation_list, R.id.navigation_calender, R.id.navigation_settings)
//        )
        binding.navView.setupWithNavController(navController)

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

    private fun setAlarm(context: Context){
        AlarmManager.register(context, 9, 35, 0)
    }
}