package com.example.third_grade_project.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.viewModel.DetailViewModel
import java.lang.IllegalArgumentException

class DetailViewModelFactory(private val repositoty: DiaryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repositoty) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}