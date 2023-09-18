package com.example.todolistapp

import android.app.ActivityManager.TaskDescription
import android.content.res.ColorStateList
import android.graphics.Paint

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder (view:View):RecyclerView.ViewHolder(view) {
    private val tvTask:TextView= view.findViewById(R.id.tvTask)
    private val cbTask:CheckBox = view.findViewById(R.id.cbTask)
    fun render(task:taskData){
        if (task.isSelected){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        }else{
            tvTask.paintFlags = tvTask.paintFlags and  Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        cbTask.isChecked=task.isSelected
        tvTask.text=task.name

        val color= when (task.category){
            TaskCategory.Bussines -> R.color.business_category
            TaskCategory.Other -> R.color.other_category
            TaskCategory.Personal -> R.color.personal_category
        }
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context,color)
        )
    }


}