package com.example.third_grade_project.view.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.third_grade_project.R
import com.example.third_grade_project.model.Base
import com.example.third_grade_project.databinding.FragmentHomeBinding
import com.example.third_grade_project.retrofit.WeatherClient
import com.example.third_grade_project.viewModel.HomeViewModel
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val currentDateTime = Calendar.getInstance().time
    private var nowDate = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(currentDateTime)
    private var nowTime = SimpleDateFormat("HH:mm", Locale.KOREA).format(currentDateTime)

    private val homeViewModel : HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.diaryFlow.observe(viewLifecycleOwner) {
            Log.d("Logd", it.size.toString())               // it은 일기 리스트를 나타냄
            if(it.any { diary -> nowDate == diary.date }){
                binding.diaryCheckAni.visibility = View.VISIBLE
                binding.diaryNoCheckAni.visibility =View.GONE
            } else{
                binding.diaryCheckAni.visibility = View.GONE
                binding.diaryNoCheckAni.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        getWeather()

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
}