package com.example.third_grade_project.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.third_grade_project.R
import kotlinx.android.synthetic.main.activity_choice.*

class ChoiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        writing_next_btn.setOnClickListener {
            startActivity(Intent(this@ChoiceActivity, AddActivity::class.java))
        }


    }
}