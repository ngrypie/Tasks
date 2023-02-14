package com.sergeygolovkin.tasks.ui.tasklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sergeygolovkin.domain.entity.Task
import com.sergeygolovkin.tasks.databinding.TaskItemBinding

/**
 * Адаптер данных для списка
 */
class TaskListAdapter(private val onCheckBoxClickHandler: (Task) -> Unit): RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    private var items = listOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        holder.binding.text.text = data.text
        holder.binding.checkbox.isChecked = data.completed
        holder.binding.checkbox.setOnClickListener {
            onCheckBoxClickHandler.invoke(data)
        }
    }

    fun setData(list: List<Task>) {
        val callback = DiffUtilCallback(items, list)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
        items = list
    }

    class ViewHolder(val binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root)

    class DiffUtilCallback(private val oldList: List<Task>, private val newList: List<Task>): DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}