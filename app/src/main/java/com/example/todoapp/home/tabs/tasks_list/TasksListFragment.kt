package com.example.todoapp.home.tabs.tasks_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapp.EditTask
import com.example.todoapp.MyDataBase
import com.example.todoapp.databinding.FragmentSettingsBinding
import com.example.todoapp.databinding.FragmentTasksListBinding
import com.example.todoapp.entities.Task
import com.google.android.material.snackbar.Snackbar
import com.example.todoapp.home.tabs.tasks_list.TasksAdapter
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.Calendar

class TasksListFragment:Fragment() {

    lateinit var viewBinding: FragmentTasksListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentTasksListBinding.inflate(inflater,container,false)
        return viewBinding.root


    }

    val selectedDate = Calendar.getInstance()
    init {
        selectedDate.set(Calendar.HOUR,0)
        selectedDate.set(Calendar.MINUTE,0)
        selectedDate.set(Calendar.SECOND,0)
        selectedDate.set(Calendar.MILLISECOND,0)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewBinding.calendarView.setSelectedDate(
            CalendarDay.today()
        )

        viewBinding.calendarView.setOnDateChangedListener(OnDateSelectedListener{

            widget, date, selected ->

            if(selected){
                selectedDate.set(Calendar.YEAR,date.year)
                selectedDate.set(Calendar.MONTH,date.month-1)
                selectedDate.set(Calendar.DAY_OF_MONTH,date.day)
                LoadTasksFromDataBase()
            }


        })


        tasksAdapter.onItemClickListener = object : TasksAdapter.OnItemClickListener{
            override fun onItemClick(task: Task) {
                updateToDoTask(task)

            }


        }
        tasksAdapter.onItemDelete = object : TasksAdapter.OnItemDeleteClickListener{
            override fun onItemDeleteClick(position: Int, task: Task) {
                deleteTask(task)
            }
        }
    }

    private fun deleteTask(task: Task) {
        Snackbar.make(viewBinding.root, "Task deleted Successfully", Snackbar.LENGTH_LONG)
            .show()

        MyDataBase.getInstance(requireContext())
            .tasksDao().deleteTask(task)

        LoadTasksFromDataBase()


    }



    private val tasksAdapter = TasksAdapter(null)
    private fun initViews() {
        viewBinding.recyclerView.adapter = tasksAdapter
    }

    private fun updateToDoTask(task : Task) {
        val intent = Intent(requireContext(),EditTask::class.java)
        intent.putExtra("Task",task)  //used Serializable in Class Task
        startActivity(intent)
    }



    override fun onStart() {
        super.onStart()
        LoadTasksFromDataBase()
    }


     fun LoadTasksFromDataBase() {
         context?.let {
             val tasks = MyDataBase.getInstance(it)
                 .tasksDao().getTaskByDate(selectedDate.timeInMillis)
             tasksAdapter.updateTasks(tasks)
         }

    }



}