package com.example.todoapp

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.dao.TasksDao
import com.example.todoapp.entities.Task

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class MyDataBase:RoomDatabase()  {
abstract fun tasksDao():TasksDao

companion object{
    private var instance:MyDataBase?=null

    fun getInstance(context:Context):MyDataBase{  // حتة ال context
        if(instance==null){
            // initialize
            instance = Room.databaseBuilder(context,MyDataBase::class.java,
                "tasksDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        return instance!!
    }
}

}