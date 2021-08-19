package com.example.todo.service

import com.example.todo.database.Todo
import com.example.todo.database.convertTodoDto
import com.example.todo.model.http.TodoDto
import com.example.todo.model.http.convertTodo
import com.example.todo.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service

@Service
class TodoListService(
    val todoRepositoryImpl: TodoRepositoryImpl
) {

    //C
    fun create(todoDto: TodoDto): TodoDto?{
        return todoDto.let{
            Todo().convertTodoDto(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodo(it)
        }
    }

    //R
    fun read(index:Int?): TodoDto? {
        return todoRepositoryImpl.findOne(index)?.let {
            TodoDto().convertTodo(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll().map{
            TodoDto().convertTodo(it)
        }.toMutableList()
    }

    //U

    fun update(todoDto: TodoDto): TodoDto?{
        return todoDto.let{
            Todo().convertTodoDto(it)
        }.let {
            todoRepositoryImpl.save(it)?.let {
                TodoDto().convertTodo(it)
            }
        }
    }

    //D
    fun delete(index:Int): Boolean {
        return todoRepositoryImpl.delete(index)
    }
}