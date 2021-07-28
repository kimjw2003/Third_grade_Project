package com.example.third_grade_project.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.third_grade_project.Application
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.FragmentSettingBinding
import com.example.third_grade_project.viewModel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private lateinit var binding : FragmentSettingBinding

    private lateinit var settingViewmodel : SettingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingViewmodel = ViewModelProvider(this).get(SettingViewModel::class.java)
        binding.mySettingViewModel = settingViewmodel
        binding.lifecycleOwner = this

        binding.alarmSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (binding.alarmSwitch.isChecked) {
                Application.prefs.setString("noti", "checked")
                Log.d("Logd", "checked")
            } else {
                Application.prefs.setString("noti", "notChecked")
                Log.d("Logd", "Notchecked")
            }
        }

        val checkable : String = Application.prefs.getString("noti", "")

        if(checkable == "checked"){
            binding.alarmSwitch.setChecked(true)
        } else{
            binding.alarmSwitch.setChecked(false)
        }




    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)

        return binding.root
    }
}