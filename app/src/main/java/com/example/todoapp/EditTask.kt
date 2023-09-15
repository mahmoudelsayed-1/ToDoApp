package com.example.todoapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.EditTaskBinding
import com.example.todoapp.entities.Task
import com.example.todoapp.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat


import java.util.Calendar
import java.util.Date

class EditTask : AppCompatActivity() {
    lateinit var viewBinding : EditTaskBinding

   private lateinit var task:Task
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = EditTaskBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
       task = (intent.getSerializableExtra("Task")as?Task)!!
        ShowData(task)

    viewBinding.saveChanges.setOnClickListener{
        UpdateToDoList()
        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }

    }

    private fun valid(): Boolean {
        if(viewBinding.titleUpdate.text.toString().isNullOrBlank()){
            viewBinding.titleUpdate.error = "please enter title"
        }
        else{
            viewBinding.titleUpdate.error = null

        }
        if(viewBinding.descUpdate.text.toString().isNullOrBlank()){
            viewBinding.descUpdate.error = "please enter details"
        }
        else{
            viewBinding.descUpdate.error = null

        }

        return true
    }

    private fun UpdateToDoList() {
        if(!valid()) {
            return
        }

             task.title=viewBinding.titleUpdate?.text.toString()
            task.descr = viewBinding.descUpdate?.text.toString()

        MyDataBase.getInstance(this).tasksDao()
            .updateTask(task)



    }



    private fun converLongToTime(dateTime: Long?): String {
            val date = Date(dateTime!!)
            val format = SimpleDateFormat("yyy/MM/dd")
        return format.format(date)
    }

    private fun ShowData(task: Task) {
        viewBinding.titleUpdate?.setText(task.title)
        viewBinding.descUpdate?.setText(task.descr)

       // val date = converLongToTime(task.dateTime)
       // viewBinding.dateEditText.text = date
    }

    var showMsg : ShowMsg?=null

    fun interface ShowMsg{
        fun Show()
    }


}