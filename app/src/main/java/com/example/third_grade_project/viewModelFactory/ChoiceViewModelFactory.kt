package com.example.third_grade_project.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.viewModel.AddViewModel
import com.example.third_grade_project.viewModel.ChoiceViewModel
import java.lang.IllegalArgumentException

class ChoiceViewModelFactory(private val repositoty: DiaryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ChoiceViewModel::class.java)){
            return ChoiceViewModel(repositoty) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}