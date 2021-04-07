package com.example.third_grade_project.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary_data_table")
data class Diary (
    @ColumnInfo(name = "diary_title")
    var title : String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "diary_id")
    var id : Int,

    @ColumnInfo(name = "diary_content")
    var content : String
)