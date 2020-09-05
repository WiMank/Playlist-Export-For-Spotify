package com.wimank.pbfs.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.wimank.pbfs.R
import com.wimank.pbfs.di.GlideApp


@BindingAdapter("loadImage")
fun loadImage(imageView: AppCompatImageView, url: String) {
    GlideApp
        .with(imageView.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_timer)
        .error(R.drawable.ic_error)
        .into(imageView)
}

@BindingAdapter("setTrackCount")
fun setTrackCount(textView: AppCompatTextView, tracks: Int) {
    textView.text = textView.resources.getQuantityString(R.plurals.tracks, tracks, tracks)
}
