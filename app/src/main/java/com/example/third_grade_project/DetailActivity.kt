package com.example.third_grade_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.databinding.ActivityDetailBinding
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
        Log.d("Logd", "title is : "+title.toString())
        content = intent.getStringExtra("content")

        detail_title_tv.text = title
        detail_content_Tv.text = content

        detailViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            }
        })
    }
}