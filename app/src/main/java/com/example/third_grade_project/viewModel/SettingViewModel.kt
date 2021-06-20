package com.example.third_grade_project.viewModel

import androidx.databinding.Observable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.third_grade_project.db.DiaryRepository

class SettingViewModel @ViewModelInject constructor(
    private val repository: DiaryRepository
) : ViewModel(), Observable{



    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}