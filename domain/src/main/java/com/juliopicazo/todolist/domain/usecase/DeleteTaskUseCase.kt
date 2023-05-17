package com.juliopicazo.todolist.domain.usecase

import com.juliopicazo.todolist.domain.database.TaskDBRepository
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.utils.CoroutineResult
import java.lang.Exception
import javax.inject.Inject

interface DeleteTaskUseCase{
    suspend operator fun invoke(idCategory : Long, task: Task): CoroutineResult<Boolean>
}

class DeleteTaskUseCaseImpl @Inject constructor(
    private val taskDBRepository: TaskDBRepository
) : DeleteTaskUseCase{

    override suspend fun invoke(idCategory: Long, task: Task): CoroutineResult<Boolean> {
        return taskDBRepository.deleteTask(idCategory, task)
    }

}