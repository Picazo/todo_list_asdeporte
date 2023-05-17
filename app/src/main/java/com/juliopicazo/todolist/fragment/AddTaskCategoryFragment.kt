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
import com.juliopicazo.todolist.databinding.FragmentCreateCategoryTaskBinding
import com.juliopicazo.todolist.databinding.FragmentCreateTaskBinding
import com.juliopicazo.todolist.databinding.FragmentHomeBinding
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.entity.TaskCategory
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryData
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.*
import com.juliopicazo.todolist.viewmodel.TasksViewModel
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskState
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskState.*
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class AddTaskCategoryFragment : Fragment(){

    private lateinit var binding: FragmentCreateCategoryTaskBinding
    private val taskCategoriesViewModel: TaskCategoriesViewModel by viewModels()
    private val taskViewModel: TasksViewModel by viewModels()
    private val args: SelectTaskCategoryFragmentArgs by navArgs()
    private var idTaskCategory : Long = Random.nextLong()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateCategoryTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskCategoriesViewModel.state.observe({ lifecycle }, ::updateUi)
        taskViewModel.state.observe({ lifecycle }, ::updateUiTask)


        binding.apply {
            addTask.setOnClickListener {
                val taskCategoryName = etNameTask.text.toString()
                idTaskCategory = Random.nextLong()
                if(!taskCategoryName.isNullOrEmpty()){
                    taskCategoriesViewModel.insertCategory(
                        TaskCategory(
                            idTaskCategory,
                            taskCategoryName
                        )
                    )

                }else{
                    Toast.makeText(
                        requireContext(),
                        "Ingresa los datos para poder continuar",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    private fun showHome(){
        val action = AddTaskCategoryFragmentDirections.actionAddTaskCategoryFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun updateUi(data: TaskCategoryData) {
        when (data.state) {
            ADD_CATEGORY -> {
                taskViewModel.insertTask(
                    idTaskCategory,
                    Gson().fromJson(args.task, Task::class.java)
                )
            }
            else->{}
        }
    }

    private fun updateUiTask(data: TasksViewModel.TaskData) {
        when (data.state) {
            INSERT_TASK -> showHome()
            else->{}
        }
    }
}