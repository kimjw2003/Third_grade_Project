package com.example.third_grade_project.view.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.FragmentHomeBinding
import com.example.third_grade_project.view.ChoiceActivity
import com.example.third_grade_project.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val currentDateTime = Calendar.getInstance().time
    private var nowDate = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(currentDateTime)

    private lateinit var homeViewmodel : HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.myHomeViewModel = homeViewmodel
        binding.lifecycleOwner = this

        homeViewmodel.diaryFlow.observe(viewLifecycleOwner) {
            Log.d("Logd", it.size.toString())               // it은 일기 리스트를 나타냄
            if(it.any { diary -> nowDate == diary.date }){
                homeViewmodel.checkable = "1"
                binding.diaryCheckImg.setImageResource(R.drawable.check_ok)
                binding.diaryCheckBtn.text = "작성한 일기 보러가기"
                binding.diaryCheckBtn.setBackgroundResource(R.drawable.check_btn_background)
                binding.diaryCheckTv.text = "일기 작성 완료!"
                binding.diaryCheckTv.setTextColor(Color.parseColor("#80699f"))
                binding.diaryCheckBtn.setOnClickListener {
                        Navigation.findNavController(it).navigate(R.id.action_navigationHome_to_navigationList)
                }
                Log.d("Logd", "written")

                isAlarm = false
            } else{
                homeViewmodel.checkable = "0"
                binding.diaryCheckImg.setImageResource(R.drawable.check_no)
                binding.diaryCheckBtn.text = "일기 작성하러 가기"
                binding.diaryCheckBtn.setBackgroundResource(R.drawable.nocheck_btn_background)
                binding.diaryCheckTv.text = "아직 일기를 작성하지 않으셨어요!"
                binding.diaryCheckTv.setTextColor(Color.parseColor("#B6778A"))
                binding.diaryCheckBtn.setOnClickListener {
                    startActivity(Intent(activity, ChoiceActivity::class.java))
                }
                Log.d("Logd", "no written")
            }
        }

    }

    companion object{
        var isAlarm : Boolean = true
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

}