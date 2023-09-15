package com.example.todoapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    @ColumnInfo
    var title:String?=null,
    @ColumnInfo
    var descr:String?=null,
    @ColumnInfo
    var dateTime:Long?=null,
    @ColumnInfo
    var isDone:Boolean=false  // ده عشان نا اما بكريت تاسك طبيعي ببقي لسه مخلصتهاش

):Serializable
