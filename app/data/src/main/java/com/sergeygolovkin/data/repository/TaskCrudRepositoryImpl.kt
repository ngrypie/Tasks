package com.sergeygolovkin.data.repository

import com.sergeygolovkin.data.datasource.EntityDataSource
import com.sergeygolovkin.data.datasource.LocalDatasource
import com.sergeygolovkin.data.db.TaskEntity
import com.sergeygolovkin.domain.entity.Task
import com.sergeygolovkin.domain.repository.TaskCrudRepository

/**
 * Реализация репозитория для работы с единичным объектом по CRUD
 */
internal class TaskCrudRepositoryImpl(private val datasource: EntityDataSource<TaskEntity>) : TaskCrudRepository {

    override suspend fun create(entity: Task) {
        datasource.create(TaskEntity(entity.id, entity.text, entity.completed))
    }

    override suspend fun read(id: Int): Task {
        val result = datasource.read(id)
        return Task(result.id, result.text, result.completed)
    }

    override suspend fun update(task: Task) {
        datasource.update(TaskEntity(task.id, task.text, task.completed))
    }

    override suspend fun delete(task: Task) {
        datasource.delete(TaskEntity(task.id, task.text, task.completed))
    }
}