 package kim.jong.third_grade_project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import kim.jong.third_grade_project.R
import kim.jong.third_grade_project.databinding.ActivityAddBinding
import kim.jong.third_grade_project.viewModel.AddViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.Externalizable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

 @AndroidEntryPoint
class AddActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddBinding
    private lateinit var addViewModel : AddViewModel

    private val currentDateTime = Calendar.getInstance().time
    private var date = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(currentDateTime)

    @OptIn(ExperimentalTime::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mark = TimeSource.Monotonic.markNow()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add)

        addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        binding.myAddViewModel = addViewModel
        binding.lifecycleOwner = this

        binding.addDateTv.text = date

        addViewModel.date = date
        addViewModel.mood = intent.getStringExtra("mood").toString()
        Log.d("Logd", "mood is ${intent.getStringExtra("mood")}")

        binding.addNextBtn.setOnClickListener {
            Log.d("Logd", "button clicked")
            if(binding.addTitleEt.text.isEmpty() || binding.addContentEt.text.isEmpty()){
                showDialog()
                Toast.makeText(applicationContext, mark.elapsedNow().toString(), Toast.LENGTH_SHORT).show()
            }else {
                addViewModel.save()
                goSave()
            }
        }
    }


    private fun goSave(){
        addViewModel.message.observe(this, {
            it.getContentIfNotHandled()?.let { toast ->
                //Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
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
            .show()
        }

    override fun onBackPressed() {
        backDialog()
    }

    private fun backDialog(){
        SweetAlertDialog(this@AddActivity, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("작성중입니다!")
            .setContentText("지금 나가면 작성중인 내용이 저장되지 않습니다. 나가시겠습니까?")
            .setConfirmText("네")
            .setConfirmClickListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .setCancelText("아니요")
            .show()
    }

}