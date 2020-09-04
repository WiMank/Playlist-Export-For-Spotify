package com.wimank.pbfs.ui.adapter

interface PlayListItemType {
    companion object {
        const val SHIMMER = 0
        const val PLAYLIST = 1
        const val ERROR = 2
    }

    fun getType(): Int

}
