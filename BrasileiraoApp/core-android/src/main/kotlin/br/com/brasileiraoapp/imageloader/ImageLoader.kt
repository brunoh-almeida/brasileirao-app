package br.com.brasileiraoapp.imageloader

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

fun ImageView.loadImageForUrl(url: String, @DrawableRes defaultImg: Int? = null) {
    Glide.with(this)
        .load(Uri.parse(url))
        .placeholder(defaultImg ?: 0)
        .into(this)
}

@BindingAdapter("imageUrl")
fun setImageUrl(loader: ImageView?, url: String?) {
    loader?.loadImageForUrl(url ?: "")
}