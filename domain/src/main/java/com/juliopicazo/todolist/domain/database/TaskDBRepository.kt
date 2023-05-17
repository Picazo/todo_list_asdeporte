package com.juliopicazo.todolist.domain.database

import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.entity.TaskCategory
import com.juliopicazo.todolist.domain.utils.CoroutineResult

interface TaskDBRepository {

    //Task
    suspend fun getUserTasks(): CoroutineResult<List<Task>>
    suspend fun insertTask(idCategory : Long, task : Task)
    suspend fun deleteTask(idCategory : Long, task : Task) : CoroutineResult<Boolean>
    suspend fun updateTask(idCategory : Long, task : Task) : CoroutineResult<Boolean>
    suspend fun getUserTasksByIdCategory(idCategory: Long) : CoroutineResult<List<Task>>

    //TaskCategory
    suspend fun getTaskCategories(): CoroutineResult<List<TaskCategory>>
    suspend fun insertTaskCategory(category : TaskCategory) : CoroutineResult<Boolean>

}