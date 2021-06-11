package com.example.third_grade_project.viewModel

import androidx.databinding.Observable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.third_grade_project.db.DiaryRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class HomeViewModel @ViewModelInject constructor(
    private val repository: DiaryRepository
) : ViewModel(), Observable{

    lateinit var checkable : String

    val diaryFlow = liveData {
        repository.getAllDiaryFlow().collect {
            emit(it)
        }
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}