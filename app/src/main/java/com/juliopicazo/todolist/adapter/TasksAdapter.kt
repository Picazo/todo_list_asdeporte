package com.juliopicazo.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juliopicazo.todolist.adapter.TasksAdapter.OnItemClicked
import com.juliopicazo.todolist.databinding.ItemTaskBinding
import com.juliopicazo.todolist.domain.entity.Task

class TasksAdapter(
    private val tasksList: List<Task>,
    var listener : OnItemClicked) :
    RecyclerView.Adapter<TaskViewHolder>() {

    interface OnItemClicked {
        fun onItemClicked(itemClick: Task, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val taskItem: Task = tasksList[position]
        holder.bind(taskItem, listener)
    }

    override fun getItemCount(): Int = tasksList.size
}

class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task, listener: OnItemClicked) {
        binding.apply {
            taskTitle.text = task.title
            taskDescription.text = task.description

            cvTask.setOnClickListener {
                listener.onItemClicked(task, position)
            }
        }
    }
}
