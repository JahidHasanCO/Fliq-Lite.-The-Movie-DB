package dev.jahidhasanco.movieapp.presentation.activity.seeAllMovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jahidhasanco.movieapp.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.movieapp.databinding.SingleMovieBinding

import javax.inject.Inject

class SeeAllPopularMoviesAdapter
@Inject constructor():
    PagingDataAdapter<PopularMovieEntity, SeeAllPopularMoviesAdapter.ViewHolder>(Diff()) {

    class ViewHolder(val binding:SingleMovieBinding) : RecyclerView.ViewHolder(binding.root){

    }

    class Diff : DiffUtil.ItemCallback<PopularMovieEntity>(){
        override fun areItemsTheSame(oldItem: PopularMovieEntity, newItem: PopularMovieEntity): Boolean  =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PopularMovieEntity, newItem: PopularMovieEntity): Boolean =
            oldItem == newItem
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = getItem(position)
        holder.binding.movie = currentItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SingleMovieBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return SeeAllPopularMoviesAdapter.ViewHolder(binding)
    }

}