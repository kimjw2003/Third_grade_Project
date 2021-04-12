package com.example.third_grade_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.third_grade_project.databinding.ActivityDetailBinding
import com.example.third_grade_project.db.Diary
import com.example.third_grade_project.db.DiaryDb
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.viewModel.DetailViewModel
import com.example.third_grade_project.viewModelFactory.DetailViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var detailViewModel : DetailViewModel

    private var diaryDb : DiaryDb? = null
    var title : String? = null
    var content : String? = null
    var id : Int? = null
    var date : String? = null
    var mood : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val dao = DiaryDb.getInstance(application).diaryDao
        val repository = DiaryRepository(dao)
        val factory = DetailViewModelFactory(repository)
        detailViewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        binding.myDetailViewModel = detailViewModel
        binding.lifecycleOwner = this

        diaryDb = DiaryDb.getInstance(this)
        title = intent.getStringExtra("title")
        content = intent.getStringExtra("content")
        id = intent.getIntExtra("id", -1)
        date = intent.getStringExtra("date")
        mood = intent.getStringExtra("mood")

        val diary = Diary(title!!, id!!, content!!, date!!,mood!!)
        detailViewModel.diaryToDelete = diary

        detail_title_tv.text = title
        detail_content_Tv.text = content
        detail_date_Tv.text = date
        moodCheck()

        detailViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                showDialog()
            }
        })
    }

    fun showDialog(){
        SweetAlertDialog(this@DetailActivity, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("일기를 삭제하시겠습니까?")
                .setContentText("다시는 되돌릴 수 없습니다!")
                .setConfirmText("네")
                .setConfirmClickListener {
                    startActivity(Intent(this@DetailActivity, MainActivity::class.java))
                    finish()
            }.show()
    }

    fun moodCheck(){

        when(mood){
            "VeryHappy"->{
                detail_mood_Iv.setImageResource(R.drawable.very_happy_image)
            }
            "Happy"->{
                detail_mood_Iv.setImageResource(R.drawable.happy_image)
            }
            "Soso"->{
                detail_mood_Iv.setImageResource(R.drawable.soso_image)
            }
            "Sad"->{
                detail_mood_Iv.setImageResource(R.drawable.sad_image)
            }
            "Angry"->{
                detail_mood_Iv.setImageResource(R.drawable.angry_image)
            }
        }
    }


}