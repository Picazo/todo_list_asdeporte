package com.juliopicazo.todolist.domain.usecase

import com.juliopicazo.todolist.domain.database.TaskDBRepository
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.utils.CoroutineResult
import java.lang.Exception
import javax.inject.Inject

interface UpdateTaskUseCase{
    suspend operator fun invoke(idCategory : Long, task: Task): CoroutineResult<Boolean>
}

class UpdateTaskUseCaseImpl @Inject constructor(
    private val taskDBRepository: TaskDBRepository
) : UpdateTaskUseCase{

    override suspend fun invoke(idCategory: Long, task: Task): CoroutineResult<Boolean> {
        return taskDBRepository.updateTask(idCategory, task)
    }

}