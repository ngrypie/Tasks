package com.sergeygolovkin.domain.repository

import com.sergeygolovkin.domain.entity.Task
import com.sergeygolovkin.domain.entity.TaskFilter
import com.sergeygolovkin.domain.repository.base.BaseListRepository

/**
 * Репозиторий для работы со списком задач
 */
interface TaskListRepository : BaseListRepository<TaskFilter, Task>