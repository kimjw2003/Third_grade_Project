package com.example.third_grade_project.view.fragment

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
import com.example.third_grade_project.db.Diary
import com.example.third_grade_project.db.DiaryDb
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.view.DetailActivity
import com.example.third_grade_project.viewModel.CalenderViewModel
import com.example.third_grade_project.viewModelFactory.CalenderViewModelFactory

class CalenderFragment : Fragment() {

    private lateinit var binding : FragmentCalenderBinding
    private lateinit var calenderViewModel : CalenderViewModel

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

        val dao = DiaryDb.getInstance(activity?.application!!).diaryDao
        val repository = DiaryRepository(dao)
        val factory = CalenderViewModelFactory(repository)
        calenderViewModel = ViewModelProvider(this, factory).get(CalenderViewModel::class.java)
        binding.myCalenderViewModel = calenderViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView(){
        binding.calenderRcview.layoutManager = LinearLayoutManager(context)
        displayDiaryList()
    }

    private fun displayDiaryList(){
        calenderViewModel.getDiary.observe(viewLifecycleOwner)  {
            binding.calenderRcview.adapter = CalenderListRcviewAdapter(it, { selectedItem: Diary ->listItemClicked((selectedItem))})
        }
    }

    private fun listItemClicked(diary: Diary){
        calenderViewModel.initDelete(diary)
        startActivity(Intent(activity, DetailActivity::class.java))
    }
}