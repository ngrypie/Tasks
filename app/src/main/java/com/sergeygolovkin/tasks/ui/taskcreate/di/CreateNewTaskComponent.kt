package com.sergeygolovkin.tasks.ui.taskcreate.di

import android.content.Context
import com.sergeygolovkin.data.di.RoomComponent
import com.sergeygolovkin.tasks.ui.taskcreate.CreateNewTaskFragment
import dagger.BindsInstance
import dagger.Component

@CreateNewTaskScope
@Component(dependencies = [RoomComponent::class])
internal interface CreateNewTaskComponent {

    fun inject(fragment: CreateNewTaskFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context, roomComponent: RoomComponent): CreateNewTaskComponent
    }
}