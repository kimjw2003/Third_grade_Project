package com.example.third_grade_project.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.db.DiaryRepository
import java.lang.IllegalArgumentException

class AddViewModelFactory(private val repositoty: DiaryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(repositoty) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}