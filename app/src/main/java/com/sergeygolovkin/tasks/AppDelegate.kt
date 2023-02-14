package com.sergeygolovkin.tasks

import android.app.Application
import com.sergeygolovkin.data.di.DaggerRoomComponent

/**
 * Простая реализация [Application], способная держать ссылки на singleton компоненты
 */
internal class AppDelegate: Application() {

    val roomComponent by lazy {
        DaggerRoomComponent.factory().create(applicationContext)
    }

}