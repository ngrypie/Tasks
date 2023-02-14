package com.sergeygolovkin.tasks.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory<VIEW_MODEL : ViewModel> @Inject constructor(
    private val viewModel: Provider<VIEW_MODEL>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <VIEW_MODEL : ViewModel> create(modelClass: Class<VIEW_MODEL>): VIEW_MODEL {
        return viewModel.get() as VIEW_MODEL
    }
}

fun <VIEW_MODEL : ViewModel> Fragment.withFactory(
    factory: ViewModelFactory<VIEW_MODEL>,
    classNameProvider: () -> Class<VIEW_MODEL>
): VIEW_MODEL =
    ViewModelProvider(
        this,
        factory
    )[classNameProvider.invoke()]

