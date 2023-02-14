package com.sergeygolovkin.domain.repository.base

/**
 * Базовый интерфейс для загрузки списочных данных
 * (ему место в common-module, не стал выделять этот модуль ввиду простоты задания)
 */
interface BaseListRepository<FILTER: Any, DATA: Any> {

    suspend fun getList(filter: FILTER): List<DATA>
}