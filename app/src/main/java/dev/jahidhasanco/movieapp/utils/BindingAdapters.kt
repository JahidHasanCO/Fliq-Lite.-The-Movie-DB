package dev.jahidhasanco.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.jahidhasanco.movieapp.R

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, str:String){

    val options = RequestOptions.placeholderOf(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)

    Glide.with(view).setDefaultRequestOptions(options).load("https://image.tmdb.org/t/p/w780$str").into(view)
}