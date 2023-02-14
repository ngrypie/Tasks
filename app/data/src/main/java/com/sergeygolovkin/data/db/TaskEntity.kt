package com.sergeygolovkin.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sergeygolovkin.data.db.TaskEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
internal data class TaskEntity(
    @PrimaryKey @ColumnInfo("task_id") val id: Int,
    @ColumnInfo("task_text") val text: String,
    @ColumnInfo("is_completed") val completed: Boolean
) {
    companion object {
        const val TABLE_NAME = "task_entity_table"
    }
}