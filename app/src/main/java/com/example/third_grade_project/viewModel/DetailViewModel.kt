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

class DetailViewModel(private val repository: DiaryRepository) : ViewModel(), Observable {

    val diary = repository.diary
    lateinit var diaryToDelete : Diary

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = statusMessage

    fun delete(diary: Diary) = viewModelScope.launch {
        repository.delete(diary)

        statusMessage.value = Event("Diary Deleted Successfully")
    }

    fun diaryDelete(){
        delete(diaryToDelete)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}