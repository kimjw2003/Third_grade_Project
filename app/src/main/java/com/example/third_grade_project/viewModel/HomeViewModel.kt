package com.example.third_grade_project.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.third_grade_project.db.DiaryRepository
import kotlinx.coroutines.flow.collect

class HomeViewModel(private val repository: DiaryRepository) : ViewModel(){

    val diaryFlow = liveData {
        repository.getAllDiaryFlow().collect {
            emit(it)
        }
    }

//    val date = liveData{
//        val diary = repository.getAllDateDiary(Date().toString())
//        emit(diary)
//    }

}