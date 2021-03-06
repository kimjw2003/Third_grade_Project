package kim.jong.third_grade_project.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kim.jong.third_grade_project.view.DetailActivity
import kim.jong.third_grade_project.R
import kim.jong.third_grade_project.adapter.ListRcviewAdapter
import kim.jong.third_grade_project.databinding.FragmentListBinding
import kim.jong.third_grade_project.model.Diary
import kim.jong.third_grade_project.view.ChoiceActivity
import kim.jong.third_grade_project.viewModel.DiaryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding
    private lateinit var diaryviewmodel : DiaryViewModel

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
            binding.listRcView.adapter = ListRcviewAdapter(it) { selectedItem: Diary -> listItemClicked((selectedItem)) }
        }
    }

    private fun listItemClicked(diary: Diary){
        diaryviewmodel.initDelete(diary)
        startActivity(Intent(activity, DetailActivity::class.java))
    }
}