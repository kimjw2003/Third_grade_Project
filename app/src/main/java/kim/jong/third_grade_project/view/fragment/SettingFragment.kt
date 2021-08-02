package kim.jong.third_grade_project.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import kim.jong.third_grade_project.R
import kim.jong.third_grade_project.databinding.FragmentSettingBinding
import kim.jong.third_grade_project.notification.AlarmManager
import kim.jong.third_grade_project.viewModel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private lateinit var binding : FragmentSettingBinding

    private lateinit var settingViewmodel : SettingViewModel

    private val checkable : String = kim.jong.third_grade_project.Application.prefs.getString("noti", "")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingViewmodel = ViewModelProvider(this).get(SettingViewModel::class.java)
        binding.mySettingViewModel = settingViewmodel
        binding.lifecycleOwner = this

        binding.alarmSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (binding.alarmSwitch.isChecked) {
                kim.jong.third_grade_project.Application.prefs.setString("noti", "checked")
                Log.d("Logd", "Checked")
                setAlarm(requireContext())

            } else {
                kim.jong.third_grade_project.Application.prefs.setString("noti", "notChecked")
                Log.d("Logd", "Notchecked")
            }
        }

        if(checkable == "checked"){
            binding.alarmSwitch.setChecked(true)
        } else if(checkable == "notChecked"){
            binding.alarmSwitch.setChecked(false)
        }

        binding.settingNotiContent.setOnClickListener {
            showDialog()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)

        return binding.root
    }

    private fun showDialog(){
        SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE)
            .setTitleText("알림은 뭐할때 오나요?")
            .setContentText("일기를 작성하지 않으신날 21시가 되면 알림이 발송됩니다!")
            .setConfirmText("네")
            .show()
    }

    private fun setAlarm(context: Context){
        AlarmManager.register(context, 21, 0, 0)
    }

}