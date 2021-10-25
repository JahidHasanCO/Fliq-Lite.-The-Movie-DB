package dev.jahidhasanco.FliqLite.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.makeramen.roundedimageview.RoundedImageView
import dev.jahidhasanco.FliqLite.R

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, str: String) {

    val options = RequestOptions.placeholderOf(R.drawable.bg).error(R.drawable.bg)

    Glide.with(view).setDefaultRequestOptions(options).load("https://image.tmdb.org/t/p/w780$str")
        .into(view)
}

@BindingAdapter("urlToImageRound")
fun urlToImageRound(view: RoundedImageView, str: String) {

    val options = RequestOptions.placeholderOf(R.drawable.bg).error(R.drawable.bg)

    Glide.with(view).setDefaultRequestOptions(options).load("https://image.tmdb.org/t/p/w780$str")
        .into(view)
}

@BindingAdapter("urlBackdrop")
fun urlBackdrop(view: ImageView, str: String) {

    val options = RequestOptions.placeholderOf(R.drawable.bg).error(R.drawable.bg)

    Glide.with(view).setDefaultRequestOptions(options).load("https://image.tmdb.org/t/p/w780$str")
        .into(view)
}