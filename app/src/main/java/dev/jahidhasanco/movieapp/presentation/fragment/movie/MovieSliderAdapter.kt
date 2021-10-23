package dev.jahidhasanco.movieapp.presentation.fragment.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter
import dev.jahidhasanco.movieapp.databinding.SingleMovieSliderBinding
import dev.jahidhasanco.movieapp.domain.model.movie.Movie

class MovieSliderAdapter : SliderViewAdapter<MovieSliderAdapter.MyViewHolder>() {


    private var listener: ((Movie) -> Unit)? = null

    var list = mutableListOf<Movie>()

    fun setContentList(list: MutableList<Movie>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?): MyViewHolder {
        val binding =
            SingleMovieSliderBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return MyViewHolder(binding)
    }

    class MyViewHolder(val viewHolder: SingleMovieSliderBinding) :
        SliderViewAdapter.ViewHolder(viewHolder.root)


    fun itemClickListener(l: (Movie) -> Unit) {
        listener = l
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.movie = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }
    }


    override fun getCount(): Int {
        return this.list.size
    }


}