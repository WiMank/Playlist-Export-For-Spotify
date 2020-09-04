package com.wimank.pbfs.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.wimank.pbfs.domain.model.Playlist

class PlaylistDiffUtil(
    private val oldListItem: List<PlayListItemType>,
    private val newListItem: List<PlayListItemType>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldListItem.size

    override fun getNewListSize() = newListItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListItem[oldItemPosition].getType() == newListItem[newItemPosition].getType()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldListItem[oldItemPosition] is Playlist && newListItem[oldItemPosition] is Playlist) {
            val oldItem = oldListItem[oldItemPosition] as Playlist
            val newItem = newListItem[oldItemPosition] as Playlist

            if (oldItem.name == newItem.name && oldItem.image == newItem.image) {
                return true
            }

        } else {
            return false
        }

        return false
    }
}
