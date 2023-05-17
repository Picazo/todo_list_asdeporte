package com.juliopicazo.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.juliopicazo.todolist.adapter.CategoriesAdapter
import com.juliopicazo.todolist.databinding.FragmentCreateTaskBinding
import com.juliopicazo.todolist.databinding.FragmentHomeBinding
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.domain.entity.TaskCategory
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryData
import com.juliopicazo.todolist.viewmodel.TaskCategoriesViewModel.TaskCategoryState.SHOW_ALL_CATEGORIES
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class AddTaskFragment : Fragment(){

    private lateinit var binding: FragmentCreateTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            addTask.setOnClickListener {
                val taskName = etNameTask.text.toString()
                val taskDescription = etDescriptionTask.text.toString()

                if(!taskName.isNullOrEmpty() && !taskDescription.isNullOrEmpty()){
                    addTaskCategory(
                        Task(
                            Random.nextLong(),
                            taskName,
                            taskDescription
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

    private fun addTaskCategory(task: Task){
        val action = AddTaskFragmentDirections.actionAddTaskFragmentToSelectTaskCategoryFragment(Gson().toJson(task))
        findNavController().navigate(action)
    }

}