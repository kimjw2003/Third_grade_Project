package com.example.third_grade_project.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.third_grade_project.DetailActivity
import com.example.third_grade_project.R
import com.example.third_grade_project.adapter.ListRcviewAdapter
import com.example.third_grade_project.databinding.FragmentListBinding
import com.example.third_grade_project.db.Diary
import com.example.third_grade_project.db.DiaryDb
import com.example.third_grade_project.db.DiaryRepository
import com.example.third_grade_project.viewModel.DiaryViewModel
import com.example.third_grade_project.viewModelFactory.DiaryViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding
    private lateinit var diaryviewmodel : DiaryViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        writing_fabBtn.setOnClickListener {
            val intent = Intent(activity, ChoiceActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        val dao = DiaryDb.getInstance(activity?.application!!).diaryDao
        val repository = DiaryRepository(dao)
        val factory = DiaryViewModelFactory(repository)
        diaryviewmodel = ViewModelProvider(this, factory).get(DiaryViewModel::class.java)
        binding.myViewModel = diaryviewmodel
        binding.lifecycleOwner = this

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView(){
        binding.listRcView.layoutManager = LinearLayoutManager(context)
        displayDiaryList()
    }

    private fun displayDiaryList(){
        diaryviewmodel.diary.observe(viewLifecycleOwner, Observer {
            binding.listRcView.adapter = ListRcviewAdapter(it, {selectedItem:Diary->listItemClicked((selectedItem))})
        })
    }

    private fun listItemClicked(diary: Diary){
        diaryviewmodel.initDelete(diary)
        startActivity(Intent(activity, DetailActivity::class.java))
    }
}