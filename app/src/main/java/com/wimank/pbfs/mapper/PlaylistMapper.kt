package com.wimank.pbfs.mapper

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.rest.response.NetworkPlaylists
import com.wimank.pbfs.room.entity.PlaylistsEntity
import com.wimank.pbfs.util.EMPTY_STRING
import javax.inject.Inject

class PlaylistMapper @Inject constructor() : Mapper<PlaylistsEntity, Playlist> {
    override fun map(input: PlaylistsEntity): Playlist {
        return Playlist(
            name = input.playlistName,
            image = input.playlistImage,
            id = input.playlistId,
            isCollaborative = input.isCollaborative,
            isPublic = input.isCollaborative
        )
    }
}

class NetworkPlaylistMapper @Inject constructor() :
    Mapper<NetworkPlaylists, List<PlaylistsEntity>> {
    override fun map(input: NetworkPlaylists): List<PlaylistsEntity> {
        return input.items.map {
            PlaylistsEntity(
                playlistId = it.id,
                playlistName = it.name,
                playlistImage = it.images?.get(0)?.url ?: EMPTY_STRING,
                tracksUrl = it.tracks?.href ?: EMPTY_STRING,
                isPublic = it.isPublic ?: false,
                isCollaborative = it.collaborative
            )
        }
    }
}
