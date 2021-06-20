package com.example.third_grade_project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.ActivityChoiceBinding
import com.example.third_grade_project.viewModel.ChoiceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChoiceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChoiceBinding
    private lateinit var choiceViewModel : ChoiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choice)

        choiceViewModel = ViewModelProvider(this).get(ChoiceViewModel::class.java)
        binding.myChoiceViewModel = choiceViewModel
        binding.lifecycleOwner = this


        choiceViewModel.statusMessage.observe(this, {
            it.getContentIfNotHandled()?.let { mood ->
                Toast.makeText(this, "기분 : $mood", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ChoiceActivity, AddActivity::class.java)
                intent.putExtra("mood", mood)
                Log.d("Logd", mood)
                startActivity(intent)
                finish()
            }
        })
    }
}