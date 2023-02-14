package com.sergeygolovkin.domain.usecase

import com.sergeygolovkin.domain.entity.Task
import com.sergeygolovkin.domain.repository.TaskCrudRepository
import com.sergeygolovkin.domain.usecase.base.SuspendableUseCase
import javax.inject.Inject

class MarkSelectedUseCase @Inject constructor(private val repository: TaskCrudRepository): SuspendableUseCase<Task, Unit> {

    override suspend operator fun invoke(params: Task) {
        repository.update(params)
    }
}