package com.example.third_grade_project.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.third_grade_project.R
import kotlinx.android.synthetic.main.activity_choice.*
import kotlinx.android.synthetic.main.activity_choice.view.*

class ChoiceActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        angry_btn.setOnClickListener(this)
        very_happy_btn.setOnClickListener(this)
        happy_btn.setOnClickListener(this)
        soso_btn.setOnClickListener(this)
        sad_btn.setOnClickListener(this)

    }

    override fun onClick(v: View) {

        when(v.id){
            angry_btn.id-> {
                startActivity(Intent(this@ChoiceActivity, AddActivity::class.java))
            }
            very_happy_btn.id-> {
                startActivity(Intent(this@ChoiceActivity, AddActivity::class.java))
            }
            happy_btn.id-> {
                startActivity(Intent(this@ChoiceActivity, AddActivity::class.java))
            }
            soso_btn.id-> {
                startActivity(Intent(this@ChoiceActivity, AddActivity::class.java))
            }
            sad_btn.id-> {
                startActivity(Intent(this@ChoiceActivity, AddActivity::class.java))
            }
        }

    }
}