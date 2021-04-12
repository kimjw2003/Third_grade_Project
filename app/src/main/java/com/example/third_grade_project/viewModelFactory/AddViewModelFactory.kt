package com.example.third_grade_project.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.viewModel.AddViewModel
import java.lang.IllegalArgumentException

class AddViewModelFactory(private val repository: DiaryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}