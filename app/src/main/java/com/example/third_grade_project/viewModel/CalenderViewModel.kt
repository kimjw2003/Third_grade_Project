package com.example.third_grade_project.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.third_grade_project.db.Diary
import com.example.third_grade_project.db.DiaryRepository
import kotlinx.coroutines.launch

class CalenderViewModel(private val repository: DiaryRepository) : ViewModel(), Observable {

    private var isDelete = false
    private lateinit var diaryToDelete : Diary

    @Bindable
    val inputTitle = MutableLiveData<String>()
    @Bindable
    val inputContent = MutableLiveData<String>()


    fun getDiaryDate(date: String) = liveData {
        val result = repository.getAllDateDiary(date)
        emit(result)
    }

    fun initDelete(diary: Diary) = viewModelScope.launch{
        inputTitle.value = diary.title
        inputContent.value = diary.content
        isDelete = true
        diaryToDelete = diary
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}