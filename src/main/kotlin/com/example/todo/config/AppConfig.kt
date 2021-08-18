package com.example.todo.config

import com.example.todo.database.TodoDataBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    val database = TodoDataBase()
    @Bean(initMethod = "init")
    fun todoDataBase(): TodoDataBase{
        return TodoDataBase()
    }
}