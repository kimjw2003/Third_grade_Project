package com.example.third_grade_project.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.third_grade_project.Event
import com.example.third_grade_project.db.Diary
import com.example.third_grade_project.db.DiaryRepository

class ChoiceViewModel(private val repository: DiaryRepository) : ViewModel(), Observable{

    val diary = repository.diary

    @Bindable
    val inputMood = MutableLiveData<String>()

    val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = statusMessage

    private val veryHappy : String = "VeryHappy"
    private val happy : String = "Happy"
    private val soso : String = "Soso"
    private val sad : String = "Sad"
    private val angry : String = "Angry"

    fun choiceVeryHappy(){
        statusMessage.value = Event(veryHappy)
    }
    fun choiceHappy(){
        statusMessage.value = Event(happy)
    }
    fun choiceSoso(){
        statusMessage.value = Event(soso)
    }
    fun choiceSad(){
        statusMessage.value = Event(sad)
    }
    fun choiceAngry(){
        statusMessage.value = Event(angry)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}