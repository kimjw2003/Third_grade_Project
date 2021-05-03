package com.example.third_grade_project.view.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.ActivityMainBinding
import com.example.third_grade_project.model.Base
import com.example.third_grade_project.databinding.FragmentHomeBinding
import com.example.third_grade_project.retrofit.WeatherClient
import com.example.third_grade_project.view.MainActivity
import com.example.third_grade_project.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val channelId = "com.example.third_grade_project"
    private var notificationManager : NotificationManager? = null

    private lateinit var binding : FragmentHomeBinding

    private val currentDateTime = Calendar.getInstance().time
    private var nowDate = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(currentDateTime)

    private lateinit var homeViewmodel : HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.myHomeViewModel = homeViewmodel
        binding.lifecycleOwner = this

        getWeather()


        // notification settings
//        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        createNotificationChannel(channelId, "Mood Diary", "오늘 일기를 작성하지 않으셨어요!")

        homeViewmodel.diaryFlow.observe(viewLifecycleOwner) {
            Log.d("Logd", it.size.toString())               // it은 일기 리스트를 나타냄
            if(it.any { diary -> nowDate == diary.date }){
                binding.diaryCheckAni.visibility = View.VISIBLE
                binding.diaryNoCheckAni.visibility =View.GONE
            } else{
                binding.diaryCheckAni.visibility = View.GONE
                binding.diaryNoCheckAni.visibility = View.VISIBLE
            }

            if(it.any{ diary -> nowDate == diary.date }){
                Log.d("Logd", "written")
            }else{
                Log.d("Logd", "no written")
                //displayNotification()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)



        return binding.root
    }


    // 날씨 API 설정
    private fun getWeather(){
        WeatherClient.retrofitService.getCurrentWeather("BVGRPZAsOY6qzmiUtScnKkBraRMnIOJ%2F26fTMonMRLgwniHt5fwhWHMSWxV9k5eVQdY00vxTVc2jNdpWLxrEbQ%3D%3D",
                "10","1", "JSON", "11H10604"
        ).enqueue(object : retrofit2.Callback<Base> {
            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                Log.d("Logd", "onResponse")
                val res = response.body()?.response?.body?.items?.item?.get(0)
                val rain = res?.rnYn.toString()

                res?.wf.toString().also { binding.weatherTv.text = it }

                when (rain) {
                      "0" -> {
                        if (res?.wf.toString() == "맑음") {
                            binding.weatherImg.setImageResource(R.drawable.weather_sunny)
                        } else {
                            binding.weatherImg.setImageResource(R.drawable.weather_cloudy)
                        }
                    } "1", "2", "4" -> {
                            binding.weatherImg.setImageResource(R.drawable.weather_rainy)
                    } "3" -> {
                            binding.weatherImg.setImageResource(R.drawable.weather_snowy)
                    }
                }



            }
            override fun onFailure(call: Call<Base>, t: Throwable) {
                t.printStackTrace()
                Log.d("Logd", "weather failed - "+t.message.toString())
            }
        })
    }

//    private fun displayNotification(){
//        val notificationId = 45
//        val tapResultIntent = Intent(context, MainActivity::class.java)
//        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val notification = NotificationCompat.Builder(requireContext(), channelId)
//            .setContentTitle("일기장 알림")
//            .setContentText("오늘 일기를 작성하지 않으셨어요!")
//            .setSmallIcon(R.drawable.diary_logo)
//            .setAutoCancel(true)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(pendingIntent)
//            .build()
//        notificationManager?.notify(notificationId, notification)
//    }
//
//    private fun createNotificationChannel(id : String, name : String, channelDescription : String){
//        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val channel = NotificationChannel(id, name, importance).apply {
//                description = channelDescription
//            }
//            notificationManager?.createNotificationChannel(channel)
//        }
//    }


}