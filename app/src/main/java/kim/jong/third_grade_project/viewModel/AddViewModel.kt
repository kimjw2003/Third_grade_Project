package kim.jong.third_grade_project.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kim.jong.third_grade_project.model.Diary
import kim.jong.third_grade_project.db.DiaryRepository
import kotlinx.coroutines.launch

class AddViewModel @ViewModelInject constructor(
    private val repository: DiaryRepository
)  : ViewModel(), Observable {


    private var isDelete = false
    lateinit var mood : String
    lateinit var date : String


    @Bindable
    val inputTitle = MutableLiveData<String>()
    @Bindable
    val inputContent = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<kim.jong.third_grade_project.Event<String>>()

    val message : LiveData<kim.jong.third_grade_project.Event<String>>
        get() = statusMessage

    fun save(){
        val title = inputTitle.value!!
        val content = inputContent.value!!

        insert(Diary(title, 0, content, date, mood))
    }

    private fun insert(diary: Diary) = viewModelScope.launch {
        repository.insert(diary)
        isDelete = false

        statusMessage.value = kim.jong.third_grade_project.Event("일기가 성공적으로 작성되었습니다")
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}