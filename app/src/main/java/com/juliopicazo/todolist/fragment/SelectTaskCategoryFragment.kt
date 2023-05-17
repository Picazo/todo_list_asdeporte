package com.juliopicazo.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.juliopicazo.todolist.adapter.CategoriesAdapter
import com.juliopicazo.todolist.databinding.FragmentSelectTaskCategoryBinding
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.entity.TaskCategory
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryData
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.ADD_CATEGORY
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.SHOW_ALL_CATEGORIES
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.SHOW_EMPTY_CATEGORY
import com.juliopicazo.todolist.viewmodel.TasksViewModel
import com.juliopicazo.todolist.viewmodel.TasksViewModel.*
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskState.INSERT_TASK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectTaskCategoryFragment : Fragment(), CategoriesAdapter.OnItemClicked {

    private val taskCategoriesViewModel: TaskCategoriesViewModel by viewModels()
    private val taskViewModel: TasksViewModel by viewModels()
    private lateinit var binding: FragmentSelectTaskCategoryBinding
    private val args: SelectTaskCategoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectTaskCategoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskCategoriesViewModel.state.observe({ lifecycle }, ::updateUi)
        taskViewModel.state.observe({ lifecycle }, ::updateUiTask)

        binding.apply {
            addTask.setOnClickListener {
                val action = SelectTaskCategoryFragmentDirections.actionSelectTaskCategoryFragmentToAddTaskCategoryFragment(
                    args.task
                )
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        taskCategoriesViewModel.getCategories()
    }

    private fun updateUi(data: TaskCategoryData) {
        when (data.state) {
            SHOW_ALL_CATEGORIES -> refreshTaskCategories(data.taskCategories)
            SHOW_EMPTY_CATEGORY -> showViewAddCategory()
            else->{}
        }
    }

    private fun updateUiTask(data: TaskData) {
        when (data.state) {
            INSERT_TASK -> showHome()
            else->{}
        }
    }

    private fun refreshTaskCategories(tasks: List<TaskCategory>) {
        binding.rvCategories.apply {
            adapter = CategoriesAdapter(tasks, this@SelectTaskCategoryFragment)
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun showViewAddCategory(){
        binding.apply {
            rvCategories.visibility = View.GONE
            tvAddTask.visibility = View.VISIBLE
        }
    }

    override fun onItemClicked(itemClick: TaskCategory, position: Int) {
        taskViewModel.insertTask(itemClick.id, Gson().fromJson(args.task, Task::class.java))
    }

    private fun showHome(){
        Toast.makeText(
            requireContext(),
            "Tarea agregada exitosamente",
            Toast.LENGTH_SHORT
        ).show()

        val action = SelectTaskCategoryFragmentDirections.actionSelectTaskCategoryFragmentToHomeFragment()
        findNavController().navigate(action)
    }

}