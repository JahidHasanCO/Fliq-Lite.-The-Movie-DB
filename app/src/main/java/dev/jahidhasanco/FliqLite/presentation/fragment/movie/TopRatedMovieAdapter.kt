package dev.jahidhasanco.FliqLite.presentation.fragment.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.jahidhasanco.FliqLite.data.local.entity.TopRatedMovieEntity
import dev.jahidhasanco.FliqLite.databinding.SingleMovieTopRatedMovieBinding

class TopRatedMovieAdapter :
    ListAdapter<TopRatedMovieEntity, TopRatedMovieAdapter.MyViewHolder>(MovieComparator()) {

    private var listener: ((TopRatedMovieEntity) -> Unit)? = null

    class MyViewHolder(val viewHolder: SingleMovieTopRatedMovieBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            SingleMovieTopRatedMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }


    private class MovieComparator : DiffUtil.ItemCallback<TopRatedMovieEntity>() {
        override fun areItemsTheSame(oldItem: TopRatedMovieEntity, newItem: TopRatedMovieEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TopRatedMovieEntity,
            newItem: TopRatedMovieEntity
        ) =
            oldItem == newItem
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.viewHolder.movie = currentItem
        }


    }

    fun itemClickListener(l: (TopRatedMovieEntity) -> Unit) {
        listener = l
    }

}