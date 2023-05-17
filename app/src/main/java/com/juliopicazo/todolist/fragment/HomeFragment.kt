package com.juliopicazo.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.juliopicazo.todolist.adapter.CategoriesAdapter
import com.juliopicazo.todolist.databinding.FragmentHomeBinding
import com.juliopicazo.todolist.domain.entity.TaskCategory
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryData
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.ADD_CATEGORY
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.SHOW_ALL_CATEGORIES
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.SHOW_EMPTY_CATEGORY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), CategoriesAdapter.OnItemClicked {

    private val viewModel: TaskCategoriesViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

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
            addTask.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToAddTaskFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCategories()
    }

    private fun updateUi(data: TaskCategoryData) {
        when (data.state) {
            SHOW_ALL_CATEGORIES -> refreshTaskCategories(data.taskCategories)
            SHOW_EMPTY_CATEGORY -> showViewAddCategory()
            else->{}
        }
    }

    private fun refreshTaskCategories(tasks: List<TaskCategory>) {
        binding.rvCategories.apply {
            adapter = CategoriesAdapter(tasks, this@HomeFragment)
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
        val action = HomeFragmentDirections.actionHomeFragmentToTasksFragment(itemClick.id)
        findNavController().navigate(action)
    }

}