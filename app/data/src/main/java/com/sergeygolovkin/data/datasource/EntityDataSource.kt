package com.sergeygolovkin.data.datasource

/**
 * Базовый интерфейс для работы с сущностями по CRUD
 */
internal interface EntityDataSource<ENTITY : Any> {

    suspend fun create(entity: ENTITY)

    suspend fun read(entityId: Int): ENTITY

    suspend fun update(entity: ENTITY)

    suspend fun delete(entity: ENTITY)
}