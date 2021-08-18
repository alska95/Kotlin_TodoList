package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDataBase
import org.springframework.beans.factory.annotation.Autowired
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


    override fun delete(todo: Todo): Boolean {
        return try{
            todoDataBase.todoList.remove(todo)
            true
        }catch (e:Exception){
            false
        }
    }

    override fun findOne(index: Int): Todo? {
        return todoDataBase.todoList.first {
            it.index == index
        }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoList
    }
}