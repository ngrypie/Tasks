package com.sergeygolovkin.tasks.ui.tasklist.di

import android.content.Context
import com.sergeygolovkin.data.di.RoomComponent
import com.sergeygolovkin.tasks.ui.tasklist.TaskListFragment
import dagger.BindsInstance
import dagger.Component

@TaskListScope
@Component(dependencies = [RoomComponent::class])
internal interface TaskListFragmentComponent {

    fun inject(fragment: TaskListFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context, roomComponent: RoomComponent): TaskListFragmentComponent
    }
}