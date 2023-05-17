package com.juliopicazo.todolist.di

import android.content.Context
import androidx.room.Room
import com.juliopicazo.todolist.data.database.TaskDB
import com.juliopicazo.todolist.data.database.TaskDBRepositoryImpl
import com.juliopicazo.todolist.data.database.dao.TaskCategoryDao
import com.juliopicazo.todolist.data.database.dao.TaskDao
import com.juliopicazo.todolist.domain.database.TaskDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    private const val DB = "TaskDBRepository"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : TaskDB{
        return Room
            .databaseBuilder(context, TaskDB::class.java, DB)
            .build()
    }

    @Provides
    @Singleton
    fun providesTaskDao(taskDB: TaskDB) : TaskDao = taskDB.taskDao()

    @Provides
    @Singleton
    fun providesTaskCategoryDao(taskDB: TaskDB) : TaskCategoryDao = taskDB.taskCategory()

    @Provides
    fun provideTaskDBRepository(
        taskDao: TaskDao,
        taskCategoryDao: TaskCategoryDao
    ) : TaskDBRepository =
        TaskDBRepositoryImpl(
            taskDao = taskDao,
            taskCategoryDao = taskCategoryDao
        )

}