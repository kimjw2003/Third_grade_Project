package com.example.third_grade_project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.ActivityAddBinding
import com.example.third_grade_project.db.DiaryDb
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.viewModel.AddViewModel
import com.example.third_grade_project.viewModelFactory.AddViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class AddActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddBinding
    private lateinit var addViewModel : AddViewModel

    private val currentDateTime = Calendar.getInstance().time
    private var date = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(currentDateTime)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add)

        val dao = DiaryDb.getInstance(application).diaryDao
        val repository = DiaryRepository(dao)
        val factory = AddViewModelFactory(repository)
        addViewModel = ViewModelProvider(this, factory).get(AddViewModel::class.java)
        binding.myAddViewModel = addViewModel
        binding.lifecycleOwner = this

        addViewModel.date = date
        addViewModel.mood = intent.getStringExtra("mood").toString()
        Log.d("Logd", "mood is ${intent.getStringExtra("mood")}")

        addViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@AddActivity, MainActivity::class.java))
                finish()
            }
        })
    }



    private fun showDialog(){
        SweetAlertDialog(this@AddActivity, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("올바르게 작성되지 않았습니다!")
            .setContentText("제목 or 내용이 작성되지 않았습니다")
            .setConfirmText("OK")
            .setConfirmClickListener {

            }.show()
        }
    }