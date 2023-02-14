package com.sergeygolovkin.data.repository

import com.sergeygolovkin.data.datasource.ListDataSource
import com.sergeygolovkin.data.db.TaskEntity
import com.sergeygolovkin.domain.entity.Task
import com.sergeygolovkin.domain.entity.TaskFilter
import com.sergeygolovkin.domain.repository.TaskListRepository
import kotlinx.coroutines.flow.map

/**
 * Реализация репозитория списка задач, работающая с указанным [datasource]
 */
internal class TaskListRepositoryImpl(private val datasource: ListDataSource<TaskFilter, TaskEntity>): TaskListRepository {

    override suspend fun getList(filter: TaskFilter): List<Task> {
        return datasource.list(filter).map { Task(it.id, it.text, it.completed) }
    }
}