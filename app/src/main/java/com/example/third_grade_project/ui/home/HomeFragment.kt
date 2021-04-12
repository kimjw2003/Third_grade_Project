package com.example.third_grade_project.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.third_grade_project.R
import com.example.third_grade_project.data.Base
import com.example.third_grade_project.db.DiaryDb
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.retrofit.WeatherClient
import com.example.third_grade_project.viewModel.DetailViewModel
import com.example.third_grade_project.viewModel.HomeViewModel
import com.example.third_grade_project.viewModelFactory.HomeViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private val currentDateTime = Calendar.getInstance().time
    private var nowDate = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(currentDateTime)

    private val homeViewModel : HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        getWeather()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.diaryFlow.observe(viewLifecycleOwner) {
            Log.d("MyTag", it.size.toString())
            if(it.any { diary -> nowDate == diary.date }){
                diary_check_ani.visibility = View.VISIBLE
                diary_no_check_ani.visibility =View.GONE
            } else{
                diary_check_ani.visibility = View.GONE
                diary_no_check_ani.visibility = View.VISIBLE
            }
        }
        homeViewModel.date.observe(viewLifecycleOwner) {

        }
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

                res?.wf.toString().also { weather_Tv.text = it }

                when (rain) {
                      "0" -> {
                        if (res?.wf.toString() == "맑음") {
                            weather_img.setImageResource(R.drawable.weather_sunny)
                        } else {
                            weather_img.setImageResource(R.drawable.weather_cloudy)
                        }
                    } "1", "2", "4" -> {
                        weather_img.setImageResource(R.drawable.weather_rainy)
                    } "3" -> {
                        weather_img.setImageResource(R.drawable.weather_snowy)
                    }
                }



            }
            override fun onFailure(call: Call<Base>, t: Throwable) {
                t.printStackTrace()
                Log.d("Logd", "weather failed - "+t.message.toString())
            }
        })
    }
}