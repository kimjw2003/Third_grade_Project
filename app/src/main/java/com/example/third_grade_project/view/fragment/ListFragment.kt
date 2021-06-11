package com.example.third_grade_project.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.third_grade_project.view.DetailActivity
import com.example.third_grade_project.R
import com.example.third_grade_project.adapter.ListRcviewAdapter
import com.example.third_grade_project.databinding.FragmentListBinding
import com.example.third_grade_project.model.Diary
import com.example.third_grade_project.view.ChoiceActivity
import com.example.third_grade_project.viewModel.DiaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding
    private lateinit var diaryviewmodel : DiaryViewModel

    private val currentDateTime = Calendar.getInstance().time
    private var date = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(currentDateTime)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listWritingBtn.setOnClickListener {
            val intent = Intent(activity, ChoiceActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        diaryviewmodel = ViewModelProvider(this).get(DiaryViewModel::class.java)
        binding.myViewModel = diaryviewmodel
        binding.lifecycleOwner = this

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView(){
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.reverseLayout = true
        mLayoutManager.stackFromEnd = true
        binding.listRcView.layoutManager = mLayoutManager
        displayDiaryList()
    }

    private fun displayDiaryList(){
        diaryviewmodel.getDiary.observe(viewLifecycleOwner)  {
            binding.listRcView.adapter = ListRcviewAdapter(it, {selectedItem: Diary ->listItemClicked((selectedItem))})
        }
    }

    private fun listItemClicked(diary: Diary){
        diaryviewmodel.initDelete(diary)
        startActivity(Intent(activity, DetailActivity::class.java))
    }
}