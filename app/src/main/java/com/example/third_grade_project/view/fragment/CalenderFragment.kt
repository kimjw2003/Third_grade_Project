package com.example.third_grade_project.view.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.FragmentCalenderBinding

class CalenderFragment : Fragment() {

    private lateinit var binding : FragmentCalenderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calenderView.setOnDateChangeListener{view, year, month, day ->
            val monthInt : Int = month + 1

            val yearText : String = year.toString()
            var monthText : String = monthInt.toString()
            if(monthText.length == 1){
                monthText = "0$monthText"
            }
            var dayText : String = day.toString()
            if(dayText.length == 1){
                dayText = "0$dayText"
            }

            val dateInfo = "$yearText$monthText$dayText"
            Log.d("Logd", dateInfo)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calender, container, false)


        return binding.root
    }
}