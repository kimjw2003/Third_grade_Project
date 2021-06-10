package com.example.third_grade_project.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.R
import com.example.third_grade_project.model.Base
import com.example.third_grade_project.databinding.FragmentHomeBinding
import com.example.third_grade_project.retrofit.WeatherClient
import com.example.third_grade_project.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val locaionList = listOf("서울", "인천", "경기", "부산", "울산", "경남", "대구", "경북", "광주", "전남", "전북", "대전", "충남", "충북", "강원", "제주")
    private var location : String = ""

    private val currentDateTime = Calendar.getInstance().time
    private var nowDate = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(currentDateTime)

    private lateinit var homeViewmodel : HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.myHomeViewModel = homeViewmodel
        binding.lifecycleOwner = this



        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, locaionList)
        binding.locationSp.adapter = adapter
        binding.locationSp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedValue = locaionList[p2]
                location = selectedValue
                checkLocation()

                getWeather()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


        homeViewmodel.diaryFlow.observe(viewLifecycleOwner) {
            Log.d("Logd", it.size.toString())               // it은 일기 리스트를 나타냄
            if(it.any { diary -> nowDate == diary.date }){
                binding.diaryCheckAni.visibility = View.VISIBLE
                binding.diaryNoCheckAni.visibility =View.GONE
                Log.d("Logd", "written")

                isAlarm = false
            } else{
                binding.diaryCheckAni.visibility = View.GONE
                binding.diaryNoCheckAni.visibility = View.VISIBLE
                Log.d("Logd", "no written")
            }
        }
    }

    companion object{
        var isAlarm : Boolean = true

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    private fun checkLocation(){
        when(location){
            "서울"->{location = "11B10101"}
            "인천"->{location = "11B20201"}
            "경기"->{location = "11B20601"}
            "부산"->{location = "11H20201"}
            "울산"->{location = "11H20101"}
            "경남"->{location = "11H20301"}
            "대구"->{location = "11H10701"}
            "경북"->{location = "11H10202"}
            "광주"->{location = "11F20501"}
            "전남"->{location = "11F20401"}
            "전북"->{location = "11F10201"}
            "대전"->{location = "11C20401"}
            "충남"->{location = "11C20404"}
            "충북"->{location = "11C10301"}
            "강원"->{location = "11D20501"}
            "제주"->{location = "11G00201"}
        }
    }

    // 날씨 API 설정
    private fun getWeather(){
        WeatherClient.retrofitService.getCurrentWeather("BVGRPZAsOY6qzmiUtScnKkBraRMnIOJ%2F26fTMonMRLgwniHt5fwhWHMSWxV9k5eVQdY00vxTVc2jNdpWLxrEbQ%3D%3D",
                "10","1", "JSON", location
        ).enqueue(object : retrofit2.Callback<Base> {
            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                Log.d("Logd", "Now location is : $location")
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