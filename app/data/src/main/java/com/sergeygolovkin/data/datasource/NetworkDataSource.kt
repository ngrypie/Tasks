package com.sergeygolovkin.data.datasource

import com.sergeygolovkin.data.db.TaskEntity
import com.sergeygolovkin.domain.entity.TaskFilter

internal class NetworkDataSource : ListDataSource<TaskFilter, TaskEntity> {

    override suspend fun list(params: TaskFilter): List<TaskEntity> {
        TODO("Не требуется по условию задания")
    }
}