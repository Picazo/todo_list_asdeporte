package com.juliopicazo.todolist.domain.usecase

import com.juliopicazo.todolist.domain.database.TaskDBRepository
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.utils.CoroutineResult
import java.lang.Exception
import javax.inject.Inject

interface GetUserTasksUseCase{
    suspend operator fun invoke(): CoroutineResult<List<Task>>
}

class GetUserTasksUseCaseImpl @Inject constructor(
    private val taskDBRepository: TaskDBRepository
) : GetUserTasksUseCase{

    override suspend fun invoke(): CoroutineResult<List<Task>> {
        return taskDBRepository.getUserTasks()
    }

}