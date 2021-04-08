package com.example.third_grade_project.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.third_grade_project.DetailActivity
import com.example.third_grade_project.R
import com.example.third_grade_project.databinding.ListItemBinding
import com.example.third_grade_project.db.Diary

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
        binding.listItemLayout.setOnClickListener {

            itemView.setOnClickListener {
                val i = Intent(itemView.context, DetailActivity::class.java)
                i.putExtra("title", diary.title)
                Log.d("Logd", "from bind title is : "+diary.title)
                i.putExtra("content", diary.content)
                itemView.context.startActivity(i)
            }
        }
    }
}