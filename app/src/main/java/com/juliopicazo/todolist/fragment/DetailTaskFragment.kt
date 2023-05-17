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
import com.google.gson.Gson
import com.juliopicazo.todolist.R
import com.juliopicazo.todolist.databinding.FragmentDetailTaskBinding
import com.juliopicazo.todolist.domain.entity.Task
import com.juliopicazo.todolist.utils.toEditable
import com.juliopicazo.todolist.viewmodel.TasksViewModel
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskData
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskState.DELETE_TASK
import com.juliopicazo.todolist.viewmodel.TasksViewModel.TaskState.UPDATE_TASK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTaskFragment : Fragment() {

    private lateinit var binding: FragmentDetailTaskBinding
    private val args: DetailTaskFragmentArgs by navArgs()
    private val viewModel: TasksViewModel by viewModels()

    val task: Task by lazy {
        Gson().fromJson(args.task, Task::class.java)
    }

    val idCategory: Long by lazy {
        args.idCategory
    }

    var editTask : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe({ lifecycle }, ::updateUi)

        showTaskDetail()

        binding.apply {

            fbDelete.setOnClickListener {
                viewModel.deleteTask(idCategory, task)
            }

            fbEdit.setOnClickListener {
                if(!editTask){
                    disabledViews(true)
                    fbEdit.setImageDrawable(resources.getDrawable(R.drawable.ic_save))
                    editTask = true
                }else{

                    val updateTask = Task(
                        task.id,
                        etNameTask.text.toString(),
                        etDescriptionTask.text.toString()
                    )

                    viewModel.updateTaks(idCategory, updateTask)
                }
            }

        }

    }

    private fun showTaskDetail(){
        binding.apply {
            etNameTask.text = task.title.toEditable()
            etDescriptionTask.text = task.description.toEditable()

            disabledViews(false)
        }
    }

    private fun disabledViews(enabled: Boolean){
        binding.apply {

            etNameTask.isEnabled = enabled
            etNameTask.isClickable = enabled

            etDescriptionTask.isEnabled = enabled
            etDescriptionTask.isClickable = enabled

        }
    }

    private fun updateUi(data: TaskData) {
        when (data.state) {
            DELETE_TASK -> showHome("Tarea borrada con exito")
            UPDATE_TASK -> showHome("Tarea actualizada con exito")
            else -> {}
        }
    }

    private fun showHome(text: String){
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()

        val action = DetailTaskFragmentDirections.actionDetailFragmentToHomeFragment()
        findNavController().navigate(action)
    }

}