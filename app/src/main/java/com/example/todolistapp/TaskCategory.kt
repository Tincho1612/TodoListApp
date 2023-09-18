package com.example.todolistapp

sealed class TaskCategory(var isSelected:Boolean=true) {
    object Personal:TaskCategory()
    object Bussines:TaskCategory()
    object Other:TaskCategory()
}