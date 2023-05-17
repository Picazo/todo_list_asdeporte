package com.juliopicazo.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.juliopicazo.todolist.R
import com.juliopicazo.todolist.adapter.TasksAdapter
import com.juliopicazo.todolist.databinding.FragmentHomeBinding
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.viewmodel.TasksViewModel
import com.juliopicazo.todolist.viewmodel.TasksViewModel.*
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskState.SHOW_ALL_TASK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment(), TasksAdapter.OnItemClicked {

    private val viewModel: TasksViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding
    private val args: TasksFragmentArgs by navArgs()

    val idCategory: Long by lazy {
        args.idCategory
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe({ lifecycle }, ::updateUi)

        binding.apply {

            tvDescription.text = resources.getString(R.string.tasks_description)
            addTask.visibility = View.GONE

        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getTasksByCategoryId(idCategory)
    }

    private fun updateUi(data: TaskData) {
        when (data.state) {
            SHOW_ALL_TASK -> refreshTasks(data.userTasks)
            else -> {}
        }
    }

    private fun refreshTasks(tasks: List<Task>) {
        binding.rvCategories.apply {
            adapter = TasksAdapter(tasks, this@TasksFragment)
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onItemClicked(itemClick: Task, position: Int) {
        val action = TasksFragmentDirections.actionTasksFragmentToDetailTaskFragment(args.idCategory, Gson().toJson(itemClick))
        findNavController().navigate(action)
    }

}