package com.juliopicazo.todolist.domain.usecase

import com.juliopicazo.todolist.domain.database.TaskDBRepository
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.entity.TaskCategory
import com.juliopicazo.todolist.domain.utils.CoroutineResult
import java.lang.Exception
import javax.inject.Inject

interface GetTasksCategoriesUseCase{
    suspend operator fun invoke(): CoroutineResult<List<TaskCategory>>
}

class GetTasksCategoriesUseCaseImpl @Inject constructor(
    private val taskDBRepository: TaskDBRepository
) : GetTasksCategoriesUseCase{

    override suspend fun invoke(): CoroutineResult<List<TaskCategory>> {
        return taskDBRepository.getTaskCategories()
    }

}