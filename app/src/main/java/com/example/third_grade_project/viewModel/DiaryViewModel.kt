package com.example.third_grade_project.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.third_grade_project.Event
import com.example.third_grade_project.db.Diary
import com.example.third_grade_project.db.DiaryRepository
import kotlinx.coroutines.launch
import java.util.*

class DiaryViewModel(private val repository: DiaryRepository) : ViewModel(), Observable {

    private var isDelete = false
    private lateinit var diaryToDelete : Diary

    @Bindable
    val inputTitle = MutableLiveData<String>()
    @Bindable
    val inputContent = MutableLiveData<String>()
    @Bindable
    val date = MutableLiveData<Date>()

    val getDiary = liveData {
        val result = repository.getAllDiary()
        emit(result)
    }

    fun initDelete(diary: Diary){
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