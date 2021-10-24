package dev.jahidhasanco.movieapp.presentation.fragment.movie


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter
import dev.jahidhasanco.movieapp.data.local.entity.UpcomingMovieEntity
import dev.jahidhasanco.movieapp.databinding.SingleMovieSliderBinding
import dev.jahidhasanco.movieapp.presentation.activity.youtubePlayer.YoutubeVideoPlayerActivity

class MovieSliderAdapter(private val context: Context) : SliderViewAdapter<MovieSliderAdapter.MyViewHolder>() {


    var list: ArrayList<UpcomingMovieEntity> = arrayListOf()

    fun setContentList(list: List<UpcomingMovieEntity>) {

        if (this.list != list) {
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



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.movie = this.list[position]

        holder.viewHolder.playBtn.setOnClickListener {
            val intent = Intent(context, YoutubeVideoPlayerActivity::class.java)
            val movieId:String = this.list[position].id.toString()
            intent.putExtra("MovieIdPass",movieId)
            context.startActivity(intent)
        }
    }

    override fun getCount(): Int {
        return list.size
    }

}

