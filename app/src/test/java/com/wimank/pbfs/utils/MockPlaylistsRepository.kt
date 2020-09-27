package com.wimank.pbfs.utils

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.room.entity.PlaylistsEntity

object MockPlaylistsRepository {

    fun mockPlaylistsEntity(): List<PlaylistsEntity> {
        return listOf(
            PlaylistsEntity(
                playlistId = "0",
                playlistName = "F Tracks",
                playlistImage = "image 1",
                tracksUrl = "",
                tracksCount = 0,
                isPublic = false,
                isCollaborative = false
            ),
            PlaylistsEntity(
                playlistId = "1",
                playlistName = "A Tracks",
                playlistImage = "image 2",
                tracksUrl = "",
                tracksCount = 0,
                isPublic = true,
                isCollaborative = true
            )
        )
    }

    fun mockPlaylist(): List<Playlist> {
        return listOf(
            Playlist(
                name = "F Tracks",
                image = "image 1",
                tracksCount = 0,
                id = "0",
                isPublic = false,
                isCollaborative = false

            ),
            Playlist(
                name = "A Tracks",
                image = "image 2",
                tracksCount = 0,
                id = "1",
                isPublic = true,
                isCollaborative = true
            )
        )
    }
}
