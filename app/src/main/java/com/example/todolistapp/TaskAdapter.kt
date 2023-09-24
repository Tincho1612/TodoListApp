package com.example.todolistapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter ( var tasks:MutableList<taskData>,private val onTaskSelected:(Int) -> Unit,private val retornarTarea:(taskData)->Unit) :RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(tasks[position])
        holder.btnRemove.setOnClickListener{
            retornarTarea(tasks[position])
        }
        holder.itemView.setOnClickListener {
            onTaskSelected(position)
        }


    }
    private fun eliminarItem (position: Int){
        tasks.removeAt(position)
        this.notifyItemRemoved(position)
    }



}