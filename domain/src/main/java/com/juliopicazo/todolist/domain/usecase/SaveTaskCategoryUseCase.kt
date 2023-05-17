package com.juliopicazo.todolist.domain.usecase

import com.juliopicazo.todolist.domain.database.TaskDBRepository
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.entity.TaskCategory
import com.juliopicazo.todolist.domain.utils.CoroutineResult
import java.lang.Exception
import javax.inject.Inject

interface SaveTaskCategoryUseCase{
    suspend operator fun invoke(taskCategory: TaskCategory): CoroutineResult<Boolean>
}

class SaveTaskCategoryUseCaseImpl @Inject constructor(
    private val taskDBRepository: TaskDBRepository
) : SaveTaskCategoryUseCase{

    override suspend fun invoke(taskCategory: TaskCategory): CoroutineResult<Boolean> {
        return taskDBRepository.insertTaskCategory(taskCategory)
    }

}