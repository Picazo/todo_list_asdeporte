package com.juliopicazo.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juliopicazo.todolist.adapter.CategoriesAdapter.OnItemClicked
import com.juliopicazo.todolist.databinding.ItemTaskCategoryBinding
import com.juliopicazo.todolist.domain.entity.TaskCategory

class CategoriesAdapter(
    private val categoriesList: List<TaskCategory>,
    var listener : OnItemClicked) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    interface OnItemClicked {
        fun onItemClicked(itemClick: TaskCategory, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryItem: TaskCategory = categoriesList[position]
        holder.bind(categoryItem, listener)
    }

    override fun getItemCount(): Int = categoriesList.size
}

class CategoryViewHolder(private val binding: ItemTaskCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: TaskCategory, listener: OnItemClicked) {
        binding.apply {
            categoryName.text = category.name

            cvCategory.setOnClickListener {
                listener.onItemClicked(category, position)
            }
        }
    }
}
