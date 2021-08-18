package com.example.todo.model.http

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError
import java.time.LocalDateTime
import javax.validation.Validation

class TodoDtoTest {

    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest(){
        val todoDto = TodoDto().apply{
            this.title = "테스트"
            this.description = ""
            this.schedule = "2021-08-18 20:00:00"
        }

        val result = validator.validate(todoDto)

        result.forEach{
            println(it.propertyPath.last().name)
            println(it.message)

        }
        Assertions.assertEquals(true,result.isEmpty())
    }
}