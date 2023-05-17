package com.juliopicazo.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.entity.TaskCategory
import com.juliopicazo.todolist.domain.usecase.GetTasksCategoriesUseCase
import com.juliopicazo.todolist.domain.usecase.SaveTaskCategoryUseCase
import com.juliopicazo.todolist.domain.utils.CoroutineResult
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.ADD_CATEGORY
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.SHOW_ALL_CATEGORIES
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.SHOW_EMPTY_CATEGORY
import com.juliopicazo.todolist.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskCategoriesViewModel @Inject constructor(
    private val saveTaskCategoryUseCase: SaveTaskCategoryUseCase,
    private val getCategoriesUseCase: GetTasksCategoriesUseCase,
) : BaseViewModel() {

    private var _state = MutableLiveData<TaskCategoryData>()
    val state: LiveData<TaskCategoryData> = _state


    fun insertCategory(taskCategory: TaskCategory) =viewModelScope.launch {

        loading.value = true

        withContext(Dispatchers.IO) { saveTaskCategoryUseCase(taskCategory) }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    _state.value = TaskCategoryData(state = ADD_CATEGORY)
                    loading.value = false
                }

                else -> {}
            }
        }
    }

    fun getCategories() =viewModelScope.launch {

        loading.value = true

        withContext(Dispatchers.IO) { getCategoriesUseCase() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    if (result.data.isNotEmpty()) {
                        _state.value = TaskCategoryData(state = SHOW_ALL_CATEGORIES, taskCategories = result.data)
                    }else{
                        _state.value = TaskCategoryData(state = SHOW_EMPTY_CATEGORY)
                    }
                    loading.value = false
                }

                else -> {
                    _state.value = TaskCategoryData(state = SHOW_EMPTY_CATEGORY)
                }
            }
        }
    }

    data class TaskCategoryData(
        val state: TaskCategoryState,
        val userTasks: List<Task> = emptyList(),
        val taskCategories: List<TaskCategory> = emptyList(),
        val exception: Exception? = null
    )

    enum class TaskCategoryState {
        SHOW_ALL_CATEGORIES,
        ADD_CATEGORY,
        SHOW_EMPTY_CATEGORY
    }
}