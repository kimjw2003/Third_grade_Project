package com.example.third_grade_project.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.viewModel.CalenderViewModel
import java.lang.IllegalArgumentException

//class CalenderViewModelFactory(private val repository: DiaryRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(CalenderViewModel::class.java)){
//            return CalenderViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown View Model Class")
//    }
//}