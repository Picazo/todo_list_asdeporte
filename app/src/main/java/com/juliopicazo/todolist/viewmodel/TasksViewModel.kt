package com.juliopicazo.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.entity.TaskCategory
import com.juliopicazo.todolist.domain.usecase.DeleteTaskUseCase
import com.juliopicazo.todolist.domain.usecase.GetUserTasksByCategoryIdUseCase
import com.juliopicazo.todolist.domain.usecase.GetUserTasksUseCase
import com.juliopicazo.todolist.domain.usecase.SaveTaskUseCase
import com.juliopicazo.todolist.domain.usecase.UpdateTaskUseCase
import com.juliopicazo.todolist.domain.utils.CoroutineResult
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskState.DELETE_TASK
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskState.INSERT_TASK
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskState.SHOW_ALL_TASK
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskState.UPDATE_TASK
import com.juliopicazo.todolist.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getUserTasksUseCase: GetUserTasksUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getUserTasksByCategoryIdUseCase: GetUserTasksByCategoryIdUseCase,
    ) : BaseViewModel() {

    private var _state = MutableLiveData<TaskData>()
    val state: LiveData<TaskData> = _state

    fun insertTask(idCategory : Long, task : Task) =viewModelScope.launch {

        loading.value = true

        withContext(Dispatchers.IO) { saveTaskUseCase(idCategory, task) }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    _state.value = TaskData(state = INSERT_TASK)
                    loading.value = false
                }

                else -> {}
            }
        }
    }

    fun getTasksByCategoryId(idCategory: Long) =viewModelScope.launch {

        loading.value = true

        withContext(Dispatchers.IO) { getUserTasksByCategoryIdUseCase(idCategory) }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    if (result.data.isNotEmpty()) {
                        _state.value = TaskData(state = SHOW_ALL_TASK, userTasks = result.data)
                    }
                    loading.value = false
                }

                else -> {}
            }
        }
    }



    fun deleteTask(idCategory: Long, task: Task) =viewModelScope.launch {

        loading.value = true

        withContext(Dispatchers.IO) { deleteTaskUseCase(idCategory, task) }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    _state.value = TaskData(state = DELETE_TASK)
                    loading.value = false
                }

                else -> {}
            }
        }
    }

    fun updateTaks(idCategory: Long, task: Task) =viewModelScope.launch {

        loading.value = true

        withContext(Dispatchers.IO) { updateTaskUseCase(idCategory, task) }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    _state.value = TaskData(state = UPDATE_TASK)
                    loading.value = false
                }

                else -> {}
            }
        }
    }


    data class TaskData(
        val state: TaskState,
        val userTasks: List<Task> = emptyList(),
        val taskCategories: List<TaskCategory> = emptyList(),
        val exception: Exception? = null
    )

    enum class TaskState {
        SHOW_ALL_TASK,
        DELETE_TASK,
        UPDATE_TASK,
        INSERT_TASK
    }
}