package com.sergeygolovkin.domain.usecase

import com.sergeygolovkin.domain.entity.Task
import com.sergeygolovkin.domain.repository.TaskCrudRepository
import com.sergeygolovkin.domain.usecase.base.SuspendableUseCase
import javax.inject.Inject

/**
 * Юзкейс, реализующий сохранение задачи.
 * Предполагается, что новая задача не может быть создана выполненной, а в качестве идентификатора используеся текущий timestamp
 */
class SaveTaskUseCase @Inject constructor(private val repository: TaskCrudRepository)
    : SuspendableUseCase<String, Unit> {

    override suspend operator fun invoke(params: String) {
        return repository.create(Task(System.currentTimeMillis().toInt(), params, false))
    }

}