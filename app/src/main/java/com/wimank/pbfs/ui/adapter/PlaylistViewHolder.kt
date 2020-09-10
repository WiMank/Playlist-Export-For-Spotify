package com.wimank.pbfs.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.wimank.pbfs.databinding.PlaylistItemBinding
import com.wimank.pbfs.domain.model.Playlist

/**
 * ViewHolder for displaying [Playlist].
 */
class PlaylistViewHolder(private val playlistItemBinding: PlaylistItemBinding) :
    RecyclerView.ViewHolder(playlistItemBinding.root) {

    fun bind(playlist: Playlist) {
        playlistItemBinding.playlistItem = playlist
        playlistItemBinding.executePendingBindings()
    }

}
