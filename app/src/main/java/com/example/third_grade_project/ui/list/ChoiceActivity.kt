package com.example.third_grade_project.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.MainActivity
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.ActivityChoiceBinding
import com.example.third_grade_project.db.DiaryDb
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.viewModel.ChoiceViewModel
import com.example.third_grade_project.viewModelFactory.AddViewModelFactory
import com.example.third_grade_project.viewModelFactory.ChoiceViewModelFactory

class ChoiceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChoiceBinding
    private lateinit var choiceViewModel : ChoiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choice)

        val dao = DiaryDb.getInstance(application).diaryDao
        val repository = DiaryRepository(dao)
        val factory = ChoiceViewModelFactory(repository)
        choiceViewModel = ViewModelProvider(this, factory).get(ChoiceViewModel::class.java)
        binding.myChoiceViewModel = choiceViewModel
        binding.lifecycleOwner = this


        choiceViewModel.statusMessage.observe(this, Observer {
            it.getContentIfNotHandled()?.let { it ->
                Toast.makeText(this, "기분 : $it", Toast.LENGTH_SHORT).show()
                var intent = Intent(this@ChoiceActivity, AddActivity::class.java)
                intent.putExtra("mood", it)
                Log.d("Logd", it)
                startActivity(intent)
                finish()
            }
        })
    }
}