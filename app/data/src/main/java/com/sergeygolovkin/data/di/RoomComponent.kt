package com.sergeygolovkin.data.di

import android.content.Context
import com.sergeygolovkin.domain.repository.TaskCrudRepository
import com.sergeygolovkin.domain.repository.TaskListRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])
interface RoomComponent {

    fun taskListRepository(): TaskListRepository

    fun taskCrudRepository(): TaskCrudRepository

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): RoomComponent
    }
}