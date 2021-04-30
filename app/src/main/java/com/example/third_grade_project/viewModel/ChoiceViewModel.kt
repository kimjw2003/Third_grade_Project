package com.example.third_grade_project.viewModel

import androidx.databinding.Observable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.third_grade_project.Event
import com.example.third_grade_project.db.DiaryRepository
import kotlinx.coroutines.launch

class ChoiceViewModel : ViewModel(), Observable{

    val statusMessage = MutableLiveData<Event<String>>()

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