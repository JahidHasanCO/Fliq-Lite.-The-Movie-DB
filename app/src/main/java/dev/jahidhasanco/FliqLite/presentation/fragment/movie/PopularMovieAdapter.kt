package dev.jahidhasanco.FliqLite.presentation.fragment.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.jahidhasanco.FliqLite.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.FliqLite.databinding.SingleMovieBinding
import dev.jahidhasanco.FliqLite.presentation.activity.movieDetails.MovieDetailsActivity

class PopularMovieAdapter(private val context: Context) :
    ListAdapter<PopularMovieEntity, PopularMovieAdapter.MyViewHolder>(MovieComparator()) {


    class MyViewHolder(val viewHolder: SingleMovieBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            SingleMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }


    private class MovieComparator : DiffUtil.ItemCallback<PopularMovieEntity>() {
        override fun areItemsTheSame(oldItem: PopularMovieEntity, newItem: PopularMovieEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PopularMovieEntity, newItem: PopularMovieEntity) =
            oldItem == newItem
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.viewHolder.movie = currentItem
        }

        holder.viewHolder.imageViewSingleMovie.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            val movieId:String = currentItem.idMovie.toString()
            intent.putExtra("MovieIdPass",movieId)
            context.startActivity(intent)
        }

    }


}