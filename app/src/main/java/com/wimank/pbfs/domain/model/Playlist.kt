package com.wimank.pbfs.domain.model

import com.wimank.pbfs.ui.adapter.PlayListItemType
import com.wimank.pbfs.ui.adapter.PlayListItemType.Companion.PLAYLIST

data class Playlist(
    val name: String,
    val image: String,
    val tracksCount: Int,
    val id: String,
    val isPublic: Boolean,
    val isCollaborative: Boolean
) : PlayListItemType {
    override fun getType() = PLAYLIST
}
