package com.sergeygolovkin.domain.repository.base

/**
 * Базовый интерефейс, декларирующий CRUD-механику для работы с сущностями
 * (ему место в common-module, не стал выделять этот модуль ввиду простоты задания)
 */
interface BaseCrudRepository<DATA : Any> {

    suspend fun create(entity: DATA)

    suspend fun read(id: Int): DATA

    suspend fun update(task: DATA)

    suspend fun delete(task: DATA)

}