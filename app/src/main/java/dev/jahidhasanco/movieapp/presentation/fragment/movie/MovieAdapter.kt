package dev.jahidhasanco.movieapp.presentation.fragment.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jahidhasanco.movieapp.databinding.SingleMovieSliderBinding
import dev.jahidhasanco.movieapp.domain.model.movie.Movie

class MovieAdapter :RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {


    private var listener :((Movie)->Unit)?=null

    var list = mutableListOf<Movie>()

    fun setContentList(list: MutableList<Movie>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: SingleMovieSliderBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MyViewHolder {
        val binding =
            SingleMovieSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(Movie)->Unit){
        listener= l
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.movie = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}