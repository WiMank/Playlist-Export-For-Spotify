package com.wimank.pbfs.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wimank.pbfs.R
import com.wimank.pbfs.di.GlideApp
import com.wimank.pbfs.util.scroll


@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    GlideApp
        .with(imageView.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_timer)
        .error(R.drawable.ic_error)
        .into(imageView)
}

@BindingAdapter("setTrackCount")
fun setTrackCount(textView: TextView, tracks: Int) {
    textView.text = textView.resources.getQuantityString(R.plurals.tracks, tracks, tracks)
}

@BindingAdapter("recyclerViewScroll")
fun recyclerViewScroll(recyclerView: RecyclerView, scroll: Boolean) {
    if (scroll) {
        recyclerView.scroll(false)
    } else {
        recyclerView.scroll(true)
    }
}
