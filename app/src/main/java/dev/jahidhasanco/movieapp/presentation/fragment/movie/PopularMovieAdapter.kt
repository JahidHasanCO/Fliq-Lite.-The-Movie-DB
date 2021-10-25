package dev.jahidhasanco.movieapp.presentation.fragment.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.jahidhasanco.movieapp.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.movieapp.databinding.SingleMovieBinding

class PopularMovieAdapter() :
    ListAdapter<PopularMovieEntity, PopularMovieAdapter.MyViewHolder>(MovieComparator()) {

    private lateinit var mListener: onItemClickListener


    interface onItemClickListener{
        fun onItemClick(id: String)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

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

        holder.viewHolder.root.setOnClickListener {
            mListener.let {
                it.onItemClick(currentItem.idMovie.toString())
            }
        }
//        holder.viewHolder.imageViewSingleMovie.setOnClickListener {
//            val intent = Intent(context, MovieDetailsActivity::class.java)
//            val movieId:String = currentItem.idMovie.toString()
//            intent.putExtra("MovieIdPass",movieId)
//            context.startActivity(intent)
//        }

    }


}