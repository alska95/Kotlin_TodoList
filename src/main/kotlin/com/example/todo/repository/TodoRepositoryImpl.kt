package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDataBase

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl(
    val todoDataBase: TodoDataBase
) : TodoRepository{


    override fun save(todo: Todo): Todo? {

        //인덱스가 널이 아니면 업데이트 이므로
        return todo.index?.let{    index:Int->
            // update
            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = todo.updatedAt
                this.updatedAt = LocalDateTime.now()
            }
        }?:kotlin.run{
            return todo.apply {
                this.index = ++todoDataBase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()

            }.run {
                todoDataBase.todoList.add(todo)
                this
            }
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        return try {
            todoList.forEach{
                save(it)
            }
            true
        }catch (e: Exception){
            false
        }
    }


    override fun delete(index: Int): Boolean {
        return try{
            var target = findOne(index)
            todoDataBase.todoList.remove(target)
            true
        }catch (e:Exception){
            false
        }
    }

    override fun findOne(index: Int?): Todo? {
        return try{
            todoDataBase.todoList.first {
                it.index == index
            }
        }catch (e:Exception){
            null
        }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoList
    }
}