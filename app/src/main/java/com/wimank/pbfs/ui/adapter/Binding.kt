package com.wimank.pbfs.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.wimank.pbfs.R

@BindingAdapter("loadImage")
fun loadImage(imageView: AppCompatImageView, url: String) {
    Glide
        .with(imageView.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_timer)
        .error(R.drawable.ic_error)
        .into(imageView)
}
