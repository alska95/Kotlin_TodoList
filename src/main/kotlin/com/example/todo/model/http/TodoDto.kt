package com.example.todo.model.http

import com.example.todo.database.Todo
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

data class TodoDto (

    @field:ApiModelProperty(
        value = "DB INDEX",
        example = "1",
        required = false
    )
    var index:Int?=null,

    @field:ApiModelProperty(
        value = "일정명",
        example = "일정관리",
        required = true
    )
    @field:NotBlank
    var title:String?=null,

    @field:ApiModelProperty(
        value = "일정설명",
        example = "15시 카페",
        required = true
    )
    var description:String?=null,

    @field:ApiModelProperty(
        value = "일정시간",
        example = "2021-08-19 15:00:00",
        required = true
    )
    @field:NotBlank
    // yyyy-MM-dd HH:mm:ss
    var schedule:String?=null,

    var createdAt:LocalDateTime?=null,

    @field:ApiModelProperty(
        value = "수정일자",
        required = false
    )
    var updatedAt:LocalDateTime?=null
){
    @AssertTrue(message = "yyyy-MM-dd HH:mm:ss")
    fun ValidSchedule():Boolean{
        return try{
            LocalDateTime.parse(schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        }catch(e:Exception){
            false
        }
    }
}

fun TodoDto.convertTodo(todo: Todo): TodoDto {
    return TodoDto().apply {
        this.index = todo.index
        this.title = todo.title
        this.description = todo.description
        this.schedule = todo.schedule?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"))
        this.createdAt = todo.createdAt
        this.updatedAt = todo.updatedAt
    }
}