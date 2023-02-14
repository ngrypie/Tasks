package com.sergeygolovkin.domain.entity

/**
 * БЛ сущность, предоставляющая фильтр задач для осуществления поиска.
 * @property query опциональное содержимое строки поиска
 * @property completed нужно-ли возвращать выполненные задачи. true - только выполненные, false - только невыполненные
 */
data class TaskFilter(
    val query: String?,
    val completed: Boolean
)