package com.sergeygolovkin.domain.entity

/**
 * БЛ Сущность задачи
 * @property text текст задачи
 * @property completed признак готовности задачи
 */
data class Task(
    val id: Int,
    val text: String,
    val completed: Boolean
)
