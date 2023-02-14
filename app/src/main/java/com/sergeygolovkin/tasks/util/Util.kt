package com.sergeygolovkin.tasks.util

import android.app.Application
import com.sergeygolovkin.tasks.AppDelegate

internal fun Application.asComponentHolder() = this as AppDelegate