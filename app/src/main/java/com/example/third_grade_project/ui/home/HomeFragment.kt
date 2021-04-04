package com.example.third_grade_project.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.R
import com.example.third_grade_project.data.Base
import com.example.third_grade_project.retrofit.WeatherClient
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getWeather()
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    private fun getWeather(){
        WeatherClient.retrofitService.getCurrentWeather("BVGRPZAsOY6qzmiUtScnKkBraRMnIOJ%2F26fTMonMRLgwniHt5fwhWHMSWxV9k5eVQdY00vxTVc2jNdpWLxrEbQ%3D%3D",
                "10","1", "JSON", "11H10604"
        ).enqueue(object : retrofit2.Callback<Base>{
            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                Log.d("Logd", "onResponse")
                var res = response.body()?.response?.body?.items?.item?.get(0)

                weather_Tv.text = res?.wf.toString()



            }
            override fun onFailure(call: Call<Base>, t: Throwable) {
                t.printStackTrace()
                Log.d("Logd", t.message.toString())
            }

        })
    }
}