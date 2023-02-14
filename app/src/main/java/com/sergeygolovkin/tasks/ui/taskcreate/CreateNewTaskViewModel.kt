package com.sergeygolovkin.tasks.ui.taskcreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergeygolovkin.domain.usecase.SaveTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Вью-модель экрана создания новой задачи
 */
internal class CreateNewTaskViewModel @Inject constructor(private val createUseCase: SaveTaskUseCase): ViewModel() {

    private val stateInternal = MutableStateFlow(false)
    val taskSaved = stateInternal.asStateFlow()

    fun onConfirmClicked(currentInput: String) {
        if (currentInput.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                createUseCase(currentInput)
                stateInternal.emit(true)
            }
        }
    }
}