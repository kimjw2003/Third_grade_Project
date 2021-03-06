package kim.jong.third_grade_project.view

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import kim.jong.third_grade_project.R
import kim.jong.third_grade_project.databinding.ActivityDetailBinding
import kim.jong.third_grade_project.model.Diary
import kim.jong.third_grade_project.viewModel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var detailViewModel : DetailViewModel

    private var title : String? = null
    private var content : String? = null
    private var id : Int? = null
    private var date : String? = null
    private var mood : String? = null

    private var textToSpeech : TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
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

        binding.detailSpeakBtn.setOnClickListener {
            speech()
        }

        //tts ??????
        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                Log.d("Logd", "??????1")
                val result = textToSpeech?.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this@DetailActivity, "??? ????????? ???????????? ????????????.", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("Logd", "????????????")
                    //?????? ???
                    textToSpeech?.setPitch(0.8f)
                    //?????? ??????
                    textToSpeech?.setSpeechRate(0.8f)
                }
            }
        }

        binding.detailShareBtn.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"

            val testMessage =binding.detailTitleTv.text.toString() + "\n" + binding.detailContentTv.text.toString()

            sharingIntent.putExtra(Intent.EXTRA_TEXT, testMessage)

            val sharing = Intent.createChooser(sharingIntent, "????????????")
            startActivity(sharing)
        }

    }

    private fun showDialog(){
        SweetAlertDialog(this@DetailActivity, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("????????? ?????????????????????????")
                .setContentText("????????? ????????? ??? ????????????!")
                .setConfirmText("???")
                .setConfirmClickListener {
                    detailViewModel.delete(detailViewModel.diaryToDelete)
                    startActivity(Intent(this@DetailActivity, MainActivity::class.java))
                    finish()
            }.show()
    }

    private fun moodCheck(){
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

    private fun speech(){
        val speechData = binding.detailContentTv.text
        textToSpeech?.speak(speechData.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
    }



}