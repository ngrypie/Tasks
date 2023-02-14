package com.sergeygolovkin.tasks.ui.taskcreate

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.sergeygolovkin.tasks.base.ViewModelAwareBindableFragment
import com.sergeygolovkin.tasks.databinding.FragmentCreateNewTaskBinding
import com.sergeygolovkin.tasks.ui.taskcreate.di.DaggerCreateNewTaskComponent
import com.sergeygolovkin.tasks.util.asComponentHolder
import kotlinx.coroutines.flow.collect

internal class CreateNewTaskFragment: ViewModelAwareBindableFragment<CreateNewTaskViewModel, FragmentCreateNewTaskBinding>() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCreateNewTaskComponent
            .factory()
            .create(requireContext(), requireActivity().application.asComponentHolder().roomComponent)
            .inject(this)
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ViewBinding {
        return FragmentCreateNewTaskBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmButton.setOnClickListener {
            viewModel.onConfirmClicked(binding.taskTextInput.text.toString())
        }
        lifecycleScope.launchWhenCreated {
            viewModel.taskSaved.collect { saved ->
                if (saved) {
                    setFragmentResult(CONFIRM_REQUEST_KEY, Bundle().apply {
                        putBoolean(CONFIRM_REQUEST_KEY, true)
                    })
                    finish()
                }
            }
        }
        binding.taskTextInput.apply {
            requestFocus()
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.showSoftInput(this, 0)
        }
    }

    override fun getViewModelClass() = CreateNewTaskViewModel::class.java

    private fun finish() = parentFragmentManager.popBackStack()

    companion object {

        const val CONFIRM_REQUEST_KEY = "CONFIRM_REQUEST_KEY"

        fun newInstance(): CreateNewTaskFragment {
            return CreateNewTaskFragment()
        }

        fun proceedFragmentResult(bundle: Bundle): Boolean {
            return bundle.getBoolean(CONFIRM_REQUEST_KEY)
        }
    }
}