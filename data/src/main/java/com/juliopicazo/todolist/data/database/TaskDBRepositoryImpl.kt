package com.juliopicazo.todolist.data.database

import android.util.Log
import com.juliopicazo.todolist.data.database.dao.TaskCategoryDao
import com.juliopicazo.todolist.data.database.dao.TaskDao
import com.juliopicazo.todolist.data.database.entity.TaskEntity
import com.juliopicazo.todolist.data.database.mapper.mapToDataBaseTask
import com.juliopicazo.todolist.data.database.mapper.mapToDataBaseTaskCategory
import com.juliopicazo.todolist.data.database.mapper.mapToLocalTask
import com.juliopicazo.todolist.data.database.mapper.mapToLocalTaskCategory
import com.juliopicazo.todolist.domain.database.TaskDBRepository
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.entity.TaskCategory
import com.juliopicazo.todolist.domain.utils.CoroutineResult
import java.lang.Exception

class TaskDBRepositoryImpl(
    private val taskDao: TaskDao,
    private val taskCategoryDao: TaskCategoryDao
) : TaskDBRepository {

    override suspend fun getUserTasks(): CoroutineResult<List<Task>> =
        taskDao.getAllTasks().let {
            if(it.isNotEmpty()){
                CoroutineResult.Success(it.mapToLocalTask())
            }else{
                CoroutineResult.Failure(Exception())
            }
        }


    override suspend fun insertTask(idCategory : Long, task: Task) {
        taskDao.insertTask(task = task.mapToDataBaseTask(idCategory))
    }

    override suspend fun deleteTask(idCategory: Long, task: Task) : CoroutineResult<Boolean> =
        try {
            taskDao.deleteTask(task = task.mapToDataBaseTask(idCategory))
            CoroutineResult.Success(true)
        }catch (e: Exception){
            Log.d("ERROR", e.localizedMessage)
            CoroutineResult.Failure(Exception())
        }


    override suspend fun updateTask(idCategory : Long, task: Task) : CoroutineResult<Boolean> =
        try {
            taskDao.updateTask(task = task.mapToDataBaseTask(idCategory))
            CoroutineResult.Success(true)
        }catch (e: Exception){
            Log.d("ERROR", e.localizedMessage)
            CoroutineResult.Failure(Exception())
        }


    override suspend fun getUserTasksByIdCategory(idCategory: Long): CoroutineResult<List<Task>> =
        taskDao.getTasksByCategoryId(idCategory).let {
            if(it.isNotEmpty()){
                CoroutineResult.Success(it.mapToLocalTask())
            }else{
                CoroutineResult.Failure(Exception())
            }
        }


    override suspend fun getTaskCategories(): CoroutineResult<List<TaskCategory>> =
        taskCategoryDao.getAllCategories().let {
            if(it.isNotEmpty()){
                CoroutineResult.Success(it.mapToLocalTaskCategory())
            }else{
                CoroutineResult.Failure(Exception())
            }
        }

    override suspend fun insertTaskCategory(category: TaskCategory) : CoroutineResult<Boolean> =
        try {
            taskCategoryDao.insertCategory(category.mapToDataBaseTaskCategory())
            CoroutineResult.Success(true)
        }catch (e: Exception){
            Log.d("ERROR", e.localizedMessage)
            CoroutineResult.Failure(Exception())
        }

}