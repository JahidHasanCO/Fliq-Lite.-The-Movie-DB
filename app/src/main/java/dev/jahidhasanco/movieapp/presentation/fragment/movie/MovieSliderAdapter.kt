package dev.jahidhasanco.movieapp.presentation.fragment.movie


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.smarteist.autoimageslider.SliderViewAdapter
import dev.jahidhasanco.movieapp.data.local.entity.UpcomingMovieEntity
import dev.jahidhasanco.movieapp.databinding.SingleMovieSliderBinding

class MovieSliderAdapter() : SliderViewAdapter<MovieSliderAdapter.MyViewHolder>() {

    private var listener: ((UpcomingMovieEntity) -> Unit)? = null


    var list: ArrayList<UpcomingMovieEntity> = arrayListOf()

    fun setContentList(list: List<UpcomingMovieEntity>) {

        if(this.list != list){
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup?): MyViewHolder {
        val binding =
            SingleMovieSliderBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return MyViewHolder(binding)
    }

    class MyViewHolder(val viewHolder: SingleMovieSliderBinding) :
        SliderViewAdapter.ViewHolder(viewHolder.root)


    fun itemClickListener(l: (UpcomingMovieEntity) -> Unit) {
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
        return list.size
    }

}

