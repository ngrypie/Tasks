package com.sergeygolovkin.tasks.util

import android.app.Application
import com.sergeygolovkin.tasks.AppDelegate

/**
 * Представляет [Application] в качестве холдера зависимостей для дальнейших инъекций по необходимости
 */
internal fun Application.asComponentHolder() = this as AppDelegate