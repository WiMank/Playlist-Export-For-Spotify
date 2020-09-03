package com.wimank.pbfs.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.wimank.pbfs.domain.model.Playlist

class PlaylistDiffUtil(
    private val oldList: List<Playlist>,
    private val newList: List<Playlist>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
                &&
                oldList[oldItemPosition].image == newList[newItemPosition].image
    }
}
