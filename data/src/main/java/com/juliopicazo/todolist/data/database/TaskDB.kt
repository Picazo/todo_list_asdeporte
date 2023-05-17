package com.juliopicazo.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juliopicazo.todolist.data.database.dao.TaskCategoryDao
import com.juliopicazo.todolist.data.database.dao.TaskDao
import com.juliopicazo.todolist.data.database.entity.TaskCategoryEntity
import com.juliopicazo.todolist.data.database.entity.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
        TaskCategoryEntity::class
    ],
    version = 1
)

abstract class TaskDB : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun taskCategory(): TaskCategoryDao

}