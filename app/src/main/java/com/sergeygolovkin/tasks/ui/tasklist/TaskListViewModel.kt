package com.sergeygolovkin.tasks.ui.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergeygolovkin.domain.entity.Task
import com.sergeygolovkin.domain.usecase.UpdateTaskUseCase
import com.sergeygolovkin.domain.usecase.ReadAllTasksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Вью модель списка задач
 */
class TaskListViewModel @Inject constructor(
    private val loadUseCase: ReadAllTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    private val taskListInternal = MutableSharedFlow<List<Task>>(1)
    val taskList = taskListInternal.asSharedFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            loadUseCase(ReadAllTasksUseCase.Filter(completed = false, query = ""))
                .apply { taskListInternal.emit(this.reversed()) }
        }
    }

    fun onNewTaskCreated() {
        loadData()
    }

    fun onMarkTaskAsComplete(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            with(task) {
                updateTaskUseCase(task.copy(completed = !completed))
                loadData()
            }
        }
    }
}