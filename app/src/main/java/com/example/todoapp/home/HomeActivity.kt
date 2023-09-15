package com.example.todoapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.EditTask
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityHomeBinding
import com.example.todoapp.home.tabs.SettingsFragment
import com.example.todoapp.home.tabs.tasks_list.TasksListFragment
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding:ActivityHomeBinding
    var tasksListFragmentRef : TasksListFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.bottomNav.setOnItemSelectedListener {

            when(it.itemId){
                R.id.list_nav->{
                    tasksListFragmentRef = TasksListFragment()
                    ShowTasksListFragment()
                }

                R.id.setting_nav->{

                    ShowSettingsFragment()

                }
            }

            return@setOnItemSelectedListener true
        }

        viewBinding.addTask.setOnClickListener{
            ShowAddTaskBottomSheet()
        }

        viewBinding.bottomNav.selectedItemId = R.id.list_nav

    }

    private fun ShowAddTaskBottomSheet() {
        val addTask = AddTaskFragment()
        addTask.onTaskAdded = AddTaskFragment.OnTaskAddedListener {
            Snackbar.make(viewBinding.root,"Task Added Successfully",Snackbar.LENGTH_LONG)
                .show()
            // reload tasks list fragment
            tasksListFragmentRef?.LoadTasksFromDataBase()
        }
        addTask.show(supportFragmentManager,"")
    }

    private fun ShowSettingsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,SettingsFragment())
            .commit()
    }

    private fun ShowTasksListFragment() {
         supportFragmentManager.beginTransaction()
             .replace(R.id.fragment_container, tasksListFragmentRef!!)
             .commit()



    }
}