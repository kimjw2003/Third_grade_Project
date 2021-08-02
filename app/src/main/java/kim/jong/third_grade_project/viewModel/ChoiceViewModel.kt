package kim.jong.third_grade_project.viewModel

import androidx.databinding.Observable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kim.jong.third_grade_project.Event
import kotlinx.coroutines.launch

class ChoiceViewModel : ViewModel(), Observable{

    val statusMessage = MutableLiveData<kim.jong.third_grade_project.Event<String>>()

    private val veryHappy : String = "VeryHappy"
    private val happy : String = "Happy"
    private val soso : String = "Soso"
    private val sad : String = "Sad"
    private val angry : String = "Angry"

    fun choiceVeryHappy(){
        statusMessage.value = kim.jong.third_grade_project.Event(veryHappy)
    }
    fun choiceHappy(){
        statusMessage.value = kim.jong.third_grade_project.Event(happy)
    }
    fun choiceSoso(){
        statusMessage.value = kim.jong.third_grade_project.Event(soso)
    }
    fun choiceSad(){
        statusMessage.value = kim.jong.third_grade_project.Event(sad)
    }
    fun choiceAngry(){
        statusMessage.value = kim.jong.third_grade_project.Event(angry)
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}