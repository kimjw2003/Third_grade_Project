package com.example.third_grade_project.view


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.third_grade_project.Application
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.ActivityMainBinding
import com.example.third_grade_project.notification.AlarmManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var mBackWait: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        // navController
        val navController = findNavController(R.id.nav_host_fragment)
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

//    private fun setAlarm(context: Context){
//        AlarmManager.register(context, 21, 0, 0)
//    }

//    override fun onDestroy() {
//        //여기서 알람 판단
//        val checkable : String = Application.prefs.getString("noti", "")
//        Log.d("Logd", "from sharedpreference - $checkable")
//        if(checkable == "checked") {
//            setAlarm(this)
//        }
//        super.onDestroy()
//    }
}