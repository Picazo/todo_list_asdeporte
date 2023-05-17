package com.juliopicazo.todolist.domain.usecase

import com.juliopicazo.todolist.domain.database.TaskDBRepository
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.utils.CoroutineResult
import java.lang.Exception
import javax.inject.Inject

interface SaveTaskUseCase{
    suspend operator fun invoke(idCategory : Long, task: Task): CoroutineResult<List<Task>>
}

class SaveTaskUseCaseImpl @Inject constructor(
    private val taskDBRepository: TaskDBRepository
) : SaveTaskUseCase{

    override suspend fun invoke(idCategory : Long, task: Task): CoroutineResult<List<Task>> {

        taskDBRepository.insertTask(idCategory,task)

        return taskDBRepository.getUserTasks()
    }

}