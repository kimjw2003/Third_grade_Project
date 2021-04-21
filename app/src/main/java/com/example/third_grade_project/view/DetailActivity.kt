package com.example.third_grade_project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.ActivityDetailBinding
import com.example.third_grade_project.db.Diary
import com.example.third_grade_project.db.DiaryDb
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.viewModel.DetailViewModel
import com.example.third_grade_project.viewModelFactory.DetailViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var detailViewModel : DetailViewModel

    private var title : String? = null
    private var content : String? = null
    private var id : Int? = null
    private var date : String? = null
    private var mood : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val dao = DiaryDb.getInstance(application).diaryDao
        val repository = DiaryRepository(dao)
        val factory = DetailViewModelFactory(repository)
        detailViewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        binding.myDetailViewModel = detailViewModel
        binding.lifecycleOwner = this

        title = intent.getStringExtra("title")
        content = intent.getStringExtra("content")
        id = intent.getIntExtra("id", -1)
        date = intent.getStringExtra("date")
        mood = intent.getStringExtra("mood")

        val diary = Diary(title!!, id!!, content!!, date!!,mood!!)
        detailViewModel.diaryToDelete = diary

        binding.detailTitleTv.text = title
        binding.detailContentTv.text = content
        binding.detailDateTv.text = date
        moodCheck()

        binding.detailDeleteBtn.setOnClickListener {
            showDialog()
        }
    }

    fun showDialog(){
        SweetAlertDialog(this@DetailActivity, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("일기를 삭제하시겠습니까?")
                .setContentText("다시는 되돌릴 수 없습니다!")
                .setConfirmText("네")
                .setConfirmClickListener {
                    detailViewModel.delete(detailViewModel.diaryToDelete)
                    startActivity(Intent(this@DetailActivity, MainActivity::class.java))
                    finish()
            }.show()
    }

    fun moodCheck(){

        when(mood){
            "VeryHappy"->{
                binding.detailMoodIv.setImageResource(R.drawable.very_happy_image)
            }
            "Happy"->{
                binding.detailMoodIv.setImageResource(R.drawable.happy_image)
            }
            "Soso"->{
                binding.detailMoodIv.setImageResource(R.drawable.soso_image)
            }
            "Sad"->{
                binding.detailMoodIv.setImageResource(R.drawable.sad_image)
            }
            "Angry"->{
                binding.detailMoodIv.setImageResource(R.drawable.angry_image)
            }
        }
    }
}