package com.sergeygolovkin.data.di

import android.content.Context
import androidx.room.Room
import com.sergeygolovkin.data.datasource.LocalDatasource
import com.sergeygolovkin.data.db.DATABASE_NAME
import com.sergeygolovkin.data.db.TaskListDatabase
import com.sergeygolovkin.data.repository.TaskCrudRepositoryImpl
import com.sergeygolovkin.data.repository.TaskListRepositoryImpl
import com.sergeygolovkin.domain.repository.TaskCrudRepository
import com.sergeygolovkin.domain.repository.TaskListRepository
import dagger.Module
import dagger.Provides

@Module
internal class RoomModule {

    @Provides
    fun provideRooomDatabase(context: Context): TaskListDatabase {
        return Room.databaseBuilder(context, TaskListDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    fun provideDatasorce(database: TaskListDatabase): LocalDatasource {
        return LocalDatasource(database = database)
    }

    @Provides
    fun provideRepository(datasource: LocalDatasource): TaskListRepository {
        return TaskListRepositoryImpl(datasource = datasource)
    }

    @Provides
    fun provideCrudRepository(datasource: LocalDatasource): TaskCrudRepository {
        return TaskCrudRepositoryImpl(datasource)
    }

}