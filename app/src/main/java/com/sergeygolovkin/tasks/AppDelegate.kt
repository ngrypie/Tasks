package com.sergeygolovkin.tasks

import android.app.Application
import com.sergeygolovkin.data.di.DaggerRoomComponent

internal class AppDelegate: Application() {

    val roomComponent by lazy {
        DaggerRoomComponent.factory().create(applicationContext)
    }

}