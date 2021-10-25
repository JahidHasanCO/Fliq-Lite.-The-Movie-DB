package dev.jahidhasanco.FliqLite.presentation.fragment.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.jahidhasanco.FliqLite.R
import dev.jahidhasanco.FliqLite.databinding.SingleCategoryBinding

class CategoryAdapter(private val categories: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    class ViewHolder(val itemView: SingleCategoryBinding) : RecyclerView.ViewHolder(itemView.root){
        val name = itemView.findViewById<TextView>(R.id.cateGoryName)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SingleCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CategoryAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = categories[position]

    }

    override fun getItemCount(): Int {
        return categories.size
    }
}