package com.example.third_grade_project.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.third_grade_project.db.Diary
import com.example.third_grade_project.db.DiaryRepository

class ChoiceViewModel(private val repository: DiaryRepository) : ViewModel(), Observable{

    val diary = repository.diary
    lateinit var diaryToDelete : Diary

    @Bindable
    val inputMood = MutableLiveData<String>()



    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}