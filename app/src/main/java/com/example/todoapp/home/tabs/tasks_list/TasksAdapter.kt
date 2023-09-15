package com.example.todoapp.home.tabs.tasks_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.entities.Task
import com.zerobranch.layout.SwipeLayout

class TasksAdapter(var tasks : List<Task>?) :RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemBinding.title.text=tasks!![position].title
        holder.itemBinding.description.text=tasks!![position].descr
        holder.itemBinding.card.setOnLongClickListener(View.OnLongClickListener {

            onItemClickListener?.onItemClick(tasks!![position])
            true
        })

        holder.itemBinding.swipeLayout.setOnActionsListener(object :SwipeLayout.SwipeActionsListener {
            override fun onOpen(direction: Int, isContinuous: Boolean) {

            }

            override fun onClose() {

            }
        })
        holder.itemBinding.right.setOnClickListener{

            onItemDelete?.onItemDeleteClick(position,tasks!![position])
            true
        }
    }

    override fun getItemCount(): Int = tasks?.size?:0



    fun updateTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }


    var onItemClickListener : OnItemClickListener?=null

    fun interface OnItemClickListener{
        fun onItemClick(task:Task)

    }


    class ViewHolder(val itemBinding:ItemTaskBinding) : RecyclerView.ViewHolder(itemBinding.root){



    }

    var onItemDelete : OnItemDeleteClickListener?=null
    fun interface OnItemDeleteClickListener{
        fun onItemDeleteClick(position: Int, task: Task)
    }
}