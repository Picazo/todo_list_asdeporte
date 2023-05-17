package com.juliopicazo.todolist.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.juliopicazo.todolist.data.database.entity.TaskCategoryEntity
import com.juliopicazo.todolist.data.database.entity.TaskEntity
import com.juliopicazo.todolist.data.database.entity.TaskWithCategory

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Transaction
    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskWithCategory>

    @Transaction
    @Query("SELECT * FROM tasks WHERE categoryId = :categoryId")
    suspend fun getTasksByCategoryId(categoryId: Long): List<TaskWithCategory>

}

@Dao
interface TaskCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: TaskCategoryEntity)

    @Delete
    suspend fun deleteCategory(category: TaskCategoryEntity)

    @Query("SELECT * FROM task_categories")
    suspend fun getAllCategories(): List<TaskCategoryEntity>
}
