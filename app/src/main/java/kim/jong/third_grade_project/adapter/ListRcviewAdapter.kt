package kim.jong.third_grade_project.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kim.jong.third_grade_project.view.DetailActivity
import kim.jong.third_grade_project.R
import kim.jong.third_grade_project.databinding.ListItemBinding
import kim.jong.third_grade_project.model.Diary

class ListRcviewAdapter(private val diaryList: List<Diary>, private val clickListener: (Diary)->Unit)
    : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(diaryList[position])
    }
}

class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(diary: Diary){
        binding.listTitleTv.text = diary.title
        binding.listDateTv.text = diary.date
        binding.listItemLayout.setOnClickListener {
            val i = Intent(itemView.context, DetailActivity::class.java)
            i.putExtra("id", diary.id)
            i.putExtra("date", diary.date)
            i.putExtra("title", diary.title)
            i.putExtra("content", diary.content)
            i.putExtra("mood", diary.mood)
            itemView.context.startActivity(i)
        }
    }
}