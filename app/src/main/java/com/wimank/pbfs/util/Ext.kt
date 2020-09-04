package com.wimank.pbfs.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//Required for spotify api access
fun String.bearer(): String {
    return BEARER.plus(this)
}

//Disable or enable scrolling
fun RecyclerView.scroll(value: Boolean) {
    layoutManager = object : LinearLayoutManager(context) {
        override fun canScrollVertically(): Boolean = value
    }
}
