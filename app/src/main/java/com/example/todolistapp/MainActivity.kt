package com.example.todolistapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var tasks= mutableListOf<taskData>(
        taskData("Reunion",TaskCategory.Bussines),
        taskData("PruebaP",TaskCategory.Personal),
        taskData("PruebaOther",TaskCategory.Other)
    )
    private val categories= listOf(
        TaskCategory.Other,
        TaskCategory.Bussines,
        TaskCategory.Personal
    )
    private lateinit var rvCategories:RecyclerView
    private lateinit var categoriesAdapter:CategoriesAdapter
    private lateinit var rvTasks:RecyclerView
    private lateinit var taskAdapter:TaskAdapter
    private lateinit var fabAddTask:FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initUI()
        initListeners()
    }
    private fun initComponents(){
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask=findViewById(R.id.fabAddTask)
    }
    private fun initListeners(){
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {

        val dialog= Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val btnAddTask:Button= dialog.findViewById(R.id.btnAddTask)
        val etTask:EditText = dialog.findViewById(R.id.etTask)
        val rgCategories:RadioGroup = dialog.findViewById(R.id.rgCategories)
        dialog.show()


            btnAddTask.setOnClickListener {
                val currentTaskName = etTask.text.toString()
                if (currentTaskName.isNotEmpty()){
                    val selectedId = rgCategories.checkedRadioButtonId
                    val selectedRadioButton:RadioButton= rgCategories.findViewById(selectedId)
                    val currentCategory:TaskCategory = when(selectedRadioButton.text){
                        getString(R.string.txtBussines)-> TaskCategory.Bussines
                        getString(R.string.txtpersonal)->TaskCategory.Personal
                        else->TaskCategory.Other

                    }
                    tasks.add(taskData(etTask.text.toString(),currentCategory))
                    updateTask()
                    dialog.hide()
                }

            }

    }
    private fun updateTask(){
        val selectedCategories:List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks=tasks.filter { selectedCategories.contains(it.category) }
        taskAdapter.tasks=newTasks.toMutableList()
        taskAdapter.notifyDataSetChanged()


    }


    private fun initUI(){
        categoriesAdapter= CategoriesAdapter(categories){
            updateCategories(it)
        }
        rvCategories.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter=categoriesAdapter

        taskAdapter= TaskAdapter(tasks,{onItemSelected(it)}){removerTarea(it)}
        // no se pone lo de antes ya que es vertical por defecto
        rvTasks.layoutManager= LinearLayoutManager(this)
        rvTasks.adapter=taskAdapter
    }
    fun removerTarea(taskData: taskData){
        tasks.remove(taskData)
        taskAdapter.tasks=tasks
        taskAdapter.notifyDataSetChanged()
    }
    private fun updateCategories(posicion:Int){
        categories[posicion].isSelected = !categories[posicion].isSelected
        categoriesAdapter.notifyItemChanged(posicion)
        updateTask()
    }
    private fun onItemSelected(position:Int){
        tasks[position].isSelected=!tasks[position].isSelected
        updateTask()
    }
}
