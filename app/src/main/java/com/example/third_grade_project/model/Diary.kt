package com.example.third_grade_project.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "diary_data_table")
data class Diary (
    @ColumnInfo(name = "diary_title")
    var title : String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "diary_id")
    var id : Int,

    @ColumnInfo(name = "diary_content")
    var content : String,

    @ColumnInfo(name = "diary_date")
    var date : String,

    @ColumnInfo(name = "diary_mood")
    var mood : String
)