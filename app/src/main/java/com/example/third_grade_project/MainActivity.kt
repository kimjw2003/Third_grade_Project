package com.example.third_grade_project

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.third_grade_project.db.DiaryDb
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.viewModel.HomeViewModel
import com.example.third_grade_project.viewModelFactory.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var viewModel : HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = DiaryDb.getInstance(this)
        val dao = db.diaryDao
        val repository = DiaryRepository(dao)
        val factory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_home, R.id.navigation_list, R.id.navigation_calender, R.id.navigation_settings)
        )
        nav_view.setupWithNavController(navController)


    }
}