package com.juliopicazo.todolist.domain.usecase

import com.juliopicazo.todolist.domain.database.TaskDBRepository
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.utils.CoroutineResult
import java.lang.Exception
import javax.inject.Inject

interface GetUserTasksByCategoryIdUseCase{
    suspend operator fun invoke(idCategory: Long): CoroutineResult<List<Task>>
}

class GetUserTasksByCategoryIdUseCaseImpl @Inject constructor(
    private val taskDBRepository: TaskDBRepository
) : GetUserTasksByCategoryIdUseCase{

    override suspend fun invoke(idCategory: Long): CoroutineResult<List<Task>> {
        return taskDBRepository.getUserTasksByIdCategory(idCategory)
    }

}