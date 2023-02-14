package com.sergeygolovkin.domain.usecase

import com.sergeygolovkin.domain.entity.Task
import com.sergeygolovkin.domain.entity.TaskFilter
import com.sergeygolovkin.domain.repository.TaskListRepository
import com.sergeygolovkin.domain.usecase.base.SuspendableUseCase
import javax.inject.Inject

/**
 * Юзкейс, реализующий предоставление списка доступных задач с учетом фильтрации по выполненым
 */
class ReadAllTasksUseCase @Inject constructor(private val repository: TaskListRepository)
    : SuspendableUseCase<ReadAllTasksUseCase.Filter, List<Task>> {

    override suspend operator fun invoke(params: Filter): List<Task> {
        return repository.getList(TaskFilter(query = params.query, completed = params.completed))
    }

    /**
     * Фильтр для отбора данных по указанным признакам
     */
    data class Filter(val query: String? = null, val completed: Boolean)
}