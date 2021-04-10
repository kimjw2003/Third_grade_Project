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

class AddViewModel(private val repository: DiaryRepository)  : ViewModel(), Observable {

    val diary = repository.diary
    private var isDelete = false
    private lateinit var diaryToDelete : Diary


    @Bindable
    val inputTitle = MutableLiveData<String>()
    @Bindable
    val inputContent = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = statusMessage

    fun save(){
        val title = inputTitle.value!!
        val content = inputContent.value!!
        insert(Diary(title, 0, content, "", ""))
    }

    fun insert(diary: Diary) = viewModelScope.launch {
        repository.insert(diary)
        isDelete = false

        statusMessage.value = Event("Diary Inserted Successfully")
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}