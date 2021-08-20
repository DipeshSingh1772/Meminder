package com.example.meminder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ReminderRecord")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    @ColumnInfo(name = "Time")
    val time:String,

    @ColumnInfo(name = "Title")
    val title:String,

    @ColumnInfo(name = "Description")
    val description:String,
)
