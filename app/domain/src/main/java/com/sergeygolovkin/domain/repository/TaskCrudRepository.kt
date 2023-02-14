package com.sergeygolovkin.domain.repository

import com.sergeygolovkin.domain.entity.Task
import com.sergeygolovkin.domain.repository.base.BaseCrudRepository

/**
 * репозиторий для работы с задачами
 */
interface TaskCrudRepository : BaseCrudRepository<Task>