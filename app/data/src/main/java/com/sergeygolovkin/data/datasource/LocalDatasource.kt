package com.sergeygolovkin.data.datasource

import com.sergeygolovkin.data.db.TaskListDatabase
import com.sergeygolovkin.data.db.TaskEntity
import com.sergeygolovkin.domain.entity.TaskFilter
import kotlinx.coroutines.flow.Flow

/**
 * Источник данных, предоставляющий данные с устройства.
 */
internal class LocalDatasource(private val database: TaskListDatabase) : ListDataSource<TaskFilter, TaskEntity>,
    EntityDataSource<TaskEntity> {

    override suspend fun list(params: TaskFilter): List<TaskEntity> {
        with(database.dao()) {
            return get(buildQueryIsCompleted(params))
        }
    }

    override suspend fun create(entity: TaskEntity) {
        database.dao().create(entity)
    }

    override suspend fun read(entityId: Int): TaskEntity {
        TODO("Не требуется по условию задания")
    }

    override suspend fun delete(entity: TaskEntity) {
        database.dao().delete(entity)
    }

    override suspend fun update(entity: TaskEntity) {
        database.dao().update(entity)
    }
}