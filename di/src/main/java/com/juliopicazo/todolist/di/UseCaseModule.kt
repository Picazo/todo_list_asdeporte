package com.juliopicazo.todolist.di

import com.juliopicazo.todolist.data.database.TaskDB
import com.juliopicazo.todolist.domain.database.TaskDBRepository
import com.juliopicazo.todolist.domain.usecase.DeleteTaskUseCase
import com.juliopicazo.todolist.domain.usecase.DeleteTaskUseCaseImpl
import com.juliopicazo.todolist.domain.usecase.GetTasksCategoriesUseCase
import com.juliopicazo.todolist.domain.usecase.GetTasksCategoriesUseCaseImpl
import com.juliopicazo.todolist.domain.usecase.GetUserTasksByCategoryIdUseCase
import com.juliopicazo.todolist.domain.usecase.GetUserTasksByCategoryIdUseCaseImpl
import com.juliopicazo.todolist.domain.usecase.GetUserTasksUseCase
import com.juliopicazo.todolist.domain.usecase.GetUserTasksUseCaseImpl
import com.juliopicazo.todolist.domain.usecase.SaveTaskCategoryUseCase
import com.juliopicazo.todolist.domain.usecase.SaveTaskCategoryUseCaseImpl
import com.juliopicazo.todolist.domain.usecase.SaveTaskUseCase
import com.juliopicazo.todolist.domain.usecase.SaveTaskUseCaseImpl
import com.juliopicazo.todolist.domain.usecase.UpdateTaskUseCase
import com.juliopicazo.todolist.domain.usecase.UpdateTaskUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetUserTasksUseCase(
        taskDBRepository: TaskDBRepository
    ): GetUserTasksUseCase =
        GetUserTasksUseCaseImpl(taskDBRepository)

    @Provides
    fun provideSaveTaskUseCase(
        taskDBRepository: TaskDBRepository
    ): SaveTaskUseCase =
        SaveTaskUseCaseImpl(taskDBRepository)

    @Provides
    fun provideSaveTaskCategoryUseCase(
        taskDBRepository: TaskDBRepository
    ): SaveTaskCategoryUseCase =
        SaveTaskCategoryUseCaseImpl(taskDBRepository)

    @Provides
    fun provideDeleteTaskUseCase(
        taskDBRepository: TaskDBRepository
    ): DeleteTaskUseCase =
        DeleteTaskUseCaseImpl(taskDBRepository)

    @Provides
    fun provideGetTaskCategoriesUseCase(
        taskDBRepository: TaskDBRepository
    ): GetTasksCategoriesUseCase =
        GetTasksCategoriesUseCaseImpl(taskDBRepository)

    @Provides
    fun provideGetUserTasksByCategoryIdUseCase(
        taskDBRepository: TaskDBRepository
    ): GetUserTasksByCategoryIdUseCase =
        GetUserTasksByCategoryIdUseCaseImpl(taskDBRepository)

    @Provides
    fun provideUpdateTaskUseCase(
        taskDBRepository: TaskDBRepository
    ): UpdateTaskUseCase =
        UpdateTaskUseCaseImpl(taskDBRepository)

}