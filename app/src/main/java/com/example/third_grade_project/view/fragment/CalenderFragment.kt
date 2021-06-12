package com.example.third_grade_project.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.third_grade_project.R
import com.example.third_grade_project.adapter.CalenderListRcviewAdapter
import com.example.third_grade_project.databinding.FragmentCalenderBinding
import com.example.third_grade_project.model.Diary
import com.example.third_grade_project.view.DetailActivity
import com.example.third_grade_project.viewModel.CalenderViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CalenderFragment() : Fragment() {

    private lateinit var binding : FragmentCalenderBinding
    private lateinit var calenderViewModel : CalenderViewModel
    private lateinit var dateInfo : String

    private val currentDateTime = Calendar.getInstance().time
    private var nowDate = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(currentDateTime)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateInfo = nowDate

        getDateChange()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calender, container, false)

        calenderViewModel = ViewModelProvider(this).get(CalenderViewModel::class.java)
        binding.myCalenderViewModel = calenderViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun getDateChange(){
        initRecyclerView()
        binding.calenderView.setOnDateChangeListener{ _, year, month, day ->
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

            dateInfo = yearText+"년 "+monthText+"월 "+dayText+"일"
            Log.d("Logd", dateInfo)
            initRecyclerView()
        }
    }

    private fun initRecyclerView(){
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.reverseLayout = true
        mLayoutManager.stackFromEnd = true
        binding.calenderRcview.layoutManager = mLayoutManager
        binding.calenderDateTv.text = dateInfo

        displayDiaryList()
    }

    @SuppressLint("SetTextI18n")
    private fun displayDiaryList(){
        calenderViewModel.getDiaryDate(dateInfo).observe(viewLifecycleOwner) {
            if(calenderViewModel.checkEmpty == "result is Empty"){
                binding.rcViewEmptyCheck.text = "작성된 일기가 없습니다."
            } else{
                binding.rcViewEmptyCheck.text = ""
            }
            binding.calenderRcview.adapter = CalenderListRcviewAdapter(it, { selectedItem: Diary -> listItemClicked((selectedItem))})
        }
    }

    private fun listItemClicked(diary: Diary){
        calenderViewModel.initDelete(diary)
        startActivity(Intent(activity, DetailActivity::class.java))
    }
}