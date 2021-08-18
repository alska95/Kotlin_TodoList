package com.example.todo.repository

import com.example.todo.database.Todo

interface TodoRepository {
    fun save(todo: Todo):Todo?
    fun saveAll(todoList: MutableList<Todo>):Boolean
    fun delete(todo:Todo):Boolean
    fun findOne(index:Int): Todo?
    fun findAll():MutableList<Todo>
}

