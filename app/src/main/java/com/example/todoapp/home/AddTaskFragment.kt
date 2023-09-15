package com.example.todoapp.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.MyDataBase
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.entities.Task
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskFragment:BottomSheetDialogFragment() {
   lateinit var viewBinding:FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentAddTaskBinding.inflate(inflater,container,false)
        return viewBinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.addTaskButton.setOnClickListener{

            CreateTask()
        }

        viewBinding.textInputDateTime.setOnClickListener{
            ShowDateDialog()
        }


    }

    val calendar = Calendar.getInstance()
     fun ShowDateDialog() {
        context?.let {
            val dialog = DatePickerDialog(it)

            dialog.setOnDateSetListener{
                datePicker,year,month,day ->
                viewBinding.dateTime.setText("$day-${month+1}-$year")
                calendar.set(year,month,day)
                // to ignore time
                calendar.set(Calendar.HOUR_OF_DAY,0)
                calendar.set(Calendar.MINUTE,0)
                calendar.set(Calendar.SECOND,0)
                calendar.set(Calendar.MILLISECOND,0)
            }
            dialog.show()
        }
    }

    private fun valid(): Boolean {
        if(viewBinding.title.text.toString().isNullOrBlank()){
            viewBinding.textInputTitle.error = "please enter title"
        }
        else{
            viewBinding.textInputTitle.error = null

        }
        if(viewBinding.details.text.toString().isNullOrBlank()){
            viewBinding.textInputDetails.error = "please enter details"
        }
        else{
            viewBinding.textInputDetails.error = null

        }
        if(viewBinding.dateTime.text.toString().isNullOrBlank()){
            viewBinding.textInputDateTime.error = "please choose date"
        }
        else{
            viewBinding.textInputDateTime.error = null

        }
        return true
    }

    private fun CreateTask() {
        if(!valid()){
            return

        }

        val task = Task(
            title = viewBinding.title.text.toString(),
            descr = viewBinding.details.text.toString(),
            dateTime = calendar.timeInMillis
        )

        MyDataBase.getInstance(requireContext())
            .tasksDao().insertTask(task)

        onTaskAdded?.onAddedTask()

        dismiss()  //to dismiss dialog
    }


    var onTaskAdded:OnTaskAddedListener?=null
    fun interface OnTaskAddedListener{
        fun onAddedTask()
    }

}