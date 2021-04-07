package com.example.third_grade_project.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.third_grade_project.Event
import com.example.third_grade_project.db.Diary
import com.example.third_grade_project.db.DiaryRepository
import kotlinx.coroutines.launch

class DiaryViewModel(private val repository: DiaryRepository) : ViewModel(), Observable {

    val diary = repository.diary
    private var isDelete = false
    private lateinit var diaryToDelete : Diary

    @Bindable
    val inputTitle = MutableLiveData<String>()
    @Bindable
    val inputContent = MutableLiveData<String>()
    @Bindable
    val saveButton = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = statusMessage

    fun delete(diary: Diary) = viewModelScope.launch {
        repository.delete(diary)
        isDelete = false

        statusMessage.value = Event("Diary Deleted Successfully")
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