package com.sergeygolovkin.tasks.ui.tasklist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sergeygolovkin.tasks.R
import com.sergeygolovkin.tasks.base.ViewModelAwareBindableFragment
import com.sergeygolovkin.tasks.databinding.FragmentTaskListBinding
import com.sergeygolovkin.tasks.ui.taskcreate.CreateNewTaskFragment
import com.sergeygolovkin.tasks.ui.tasklist.di.DaggerTaskListFragmentComponent
import com.sergeygolovkin.tasks.util.asComponentHolder
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Фрагмент списка задач
 */
internal class TaskListFragment : ViewModelAwareBindableFragment<TaskListViewModel, FragmentTaskListBinding>() {

    private val adapter = TaskListAdapter {
        viewModel.onMarkTaskAsComplete(it)
    }.apply {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                if (positionStart == 0) {
                    binding.list.scrollToPosition(0)
                }
            }
        })
    }

    override fun getViewModelClass() = TaskListViewModel::class.java

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerTaskListFragmentComponent
            .factory()
            .create(requireContext(), requireActivity().application.asComponentHolder().roomComponent)
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(CreateNewTaskFragment.CONFIRM_REQUEST_KEY) { requestKey, bundle ->
            if (requestKey == CreateNewTaskFragment.CONFIRM_REQUEST_KEY && CreateNewTaskFragment.proceedFragmentResult(bundle)) {
                viewModel.onNewTaskCreated()
            }
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ViewBinding {
        return FragmentTaskListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.apply {
            adapter = this@TaskListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
        binding.toolbar.title = "Tasks"
        binding.fab.setOnClickListener {
//            viewModel.onNewTaskClicked()...
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.root_container, CreateNewTaskFragment.newInstance(), null)
                .addToBackStack(null)
                .commit()
        }

        viewModel
            .taskList
            .flowWithLifecycle(lifecycle)
            .onEach { adapter.setData(it) }
            .launchIn(lifecycleScope)
    }

    companion object {

        fun newInstance(): TaskListFragment {
            return TaskListFragment()
        }
    }
}