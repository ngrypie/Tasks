package com.sergeygolovkin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_NAME = "task_list_database"

/**
 * Room БД
 */
@Database(entities = [TaskEntity::class], version = 1)
internal abstract class TaskListDatabase : RoomDatabase() {

    abstract fun dao(): TasksDao
}