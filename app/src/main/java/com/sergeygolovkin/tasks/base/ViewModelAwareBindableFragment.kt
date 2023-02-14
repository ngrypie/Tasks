package com.sergeygolovkin.tasks.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.sergeygolovkin.tasks.util.ViewModelFactory
import com.sergeygolovkin.tasks.util.withFactory
import javax.inject.Inject

/**
 * Базовый фрагмент, реализующий инъекцию необходимой вью-модели, а так же реализующий логику связывания.
 * Призван уменьшить количество boilerplate-кода
 */
internal abstract class ViewModelAwareBindableFragment<VIEWMODEL: ViewModel, BINDING: ViewBinding>: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<VIEWMODEL>

    protected val viewModel: VIEWMODEL by lazy { withFactory(viewModelFactory, ::getViewModelClass) }

    private var _binding: BINDING? = null

    protected val binding: BINDING get() = _binding!!

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflateBinding(inflater, container, savedInstanceState).apply {
            @Suppress("UNCHECKED_CAST")
            _binding = this as BINDING
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Необходимо предоставить класс вью-модели для определения типов на этапе компиляции
     */
    protected abstract fun getViewModelClass(): Class<VIEWMODEL>

    protected abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): ViewBinding
}