package com.juliopicazo.todolist.data.database.mapper

import com.juliopicazo.todolist.data.database.entity.TaskCategoryEntity
import com.juliopicazo.todolist.data.database.entity.TaskEntity
import com.juliopicazo.todolist.data.database.entity.TaskWithCategory
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.entity.TaskCategory

fun List<TaskCategoryEntity>.mapToLocalTaskCategory(): List<TaskCategory> = this.map { it.mapToLocalTaskCategory() }

private fun TaskCategoryEntity.mapToLocalTaskCategory() =
    TaskCategory(
        id = id,
        name = name
    )

fun List<TaskWithCategory>.mapToLocalTask(): List<Task> = this.map { it.mapToLocalTask() }

private fun TaskWithCategory.mapToLocalTask() =
    Task(
        id = taskEntity.id,
        title = taskEntity.title,
        description = taskEntity.description
    )

fun Task.mapToDataBaseTask(idCategory: Long) =
    TaskEntity(
        id= id,
        title = title,
        description = description,
        categoryId = idCategory
    )

fun TaskCategory.mapToDataBaseTaskCategory() =
    TaskCategoryEntity(
        id= id,
        name = name
    )