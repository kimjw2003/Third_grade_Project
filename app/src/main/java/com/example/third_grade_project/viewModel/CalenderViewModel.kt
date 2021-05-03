package com.example.third_grade_project.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.third_grade_project.model.Diary
import com.example.third_grade_project.db.DiaryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CalenderViewModel @ViewModelInject constructor(
    private val repository: DiaryRepository
) : ViewModel(), Observable {

    private var isDelete = false
    private lateinit var diaryToDelete : Diary
    lateinit var checkEmpty : String

    @Bindable
    val inputTitle = MutableLiveData<String>()
    @Bindable
    val inputContent = MutableLiveData<String>()


    fun getDiaryDate(date: String) = liveData {
        val result = repository.getAllDateDiary(date)
        if(result.isEmpty()){
            checkEmpty = "result is Empty"
        } else{
            checkEmpty = "no Empty"
        }
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