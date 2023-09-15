package com.example.todoapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.entities.Task
import java.time.chrono.ChronoLocalDateTime

@Dao
interface TasksDao {
@Insert
    fun insertTask(task: Task)
@Update
    fun updateTask(task:Task)
@Delete
    fun deleteTask(task:Task)
@Query("select * from tasks")
    fun getAllTasks():List<Task>
@Query("select * from tasks where dateTime = :dateTime")
    fun getTaskByDate(dateTime:Long):List<Task>
}