package com.example.todolistapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoriesViewHolder (view:View):RecyclerView.ViewHolder(view) {
    private val tvCategoryName:TextView = view.findViewById(R.id.categoryName)
    private val divider:View=view.findViewById(R.id.divider)
    private val viewContainer:CardView = view.findViewById(R.id.viewCointainer)

    fun render(taskCategory: TaskCategory,onitemSelected:(Int)->Unit){

      val color=  if (taskCategory.isSelected){
            R.color.background_card
        }else{
            R.color.background_disable
        }
        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context,color))

        itemView.setOnClickListener{onitemSelected(layoutPosition)}

        when(taskCategory){
            TaskCategory.Bussines->{
                tvCategoryName.text="Negocios"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.business_category))
            }
            TaskCategory.Personal->{
                tvCategoryName.text ="Personal"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.personal_category))
            }
            TaskCategory.Other->{
                tvCategoryName.text="Otros"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.other_category))
            }
        }
    }


}