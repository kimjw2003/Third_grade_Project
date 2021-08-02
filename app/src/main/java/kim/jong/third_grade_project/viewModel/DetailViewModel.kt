package kim.jong.third_grade_project.viewModel

import androidx.databinding.Observable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kim.jong.third_grade_project.Event
import kim.jong.third_grade_project.model.Diary
import kim.jong.third_grade_project.db.DiaryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @ViewModelInject constructor(
    private val repository: DiaryRepository
) : ViewModel(), Observable {

    lateinit var diaryToDelete : Diary

    private val statusMessage = MutableLiveData<kim.jong.third_grade_project.Event<String>>()

    fun delete(diary: Diary) = viewModelScope.launch {
        repository.delete(diary)
        statusMessage.value = kim.jong.third_grade_project.Event("일기가 성공적으로 삭제되었습니다")
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}