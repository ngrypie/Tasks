package com.sergeygolovkin.data.db

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.sergeygolovkin.domain.entity.TaskFilter

/**
 * Интерфейс, описывающий взаимодействие с Room
 */
@Dao
internal interface TasksDao {

    @RawQuery
    suspend fun get(query: SupportSQLiteQuery): List<TaskEntity>

    @Insert(entity = TaskEntity::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun create(entity: TaskEntity)

    @Delete(entity = TaskEntity::class)
    suspend fun delete(entity: TaskEntity)

    @Update(entity = TaskEntity::class)
    suspend fun update(entity: TaskEntity)

    /**
     * Создать запрос в БД с учетом признака завершенности действия
     * //TODO: описать билдер/фабрику запросов для более удобного использования
     */
    fun TasksDao.buildQueryIsCompleted(filter: TaskFilter) =
        SimpleSQLiteQuery("SELECT * FROM ${TaskEntity.TABLE_NAME} WHERE is_completed = ?", arrayOf(filter.completed))
}