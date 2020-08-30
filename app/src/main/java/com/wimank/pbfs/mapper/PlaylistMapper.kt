package com.wimank.pbfs.mapper

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.room.entity.PlaylistsEntity

class PlaylistMapper : Mapper<PlaylistsEntity, Playlist> {
    override fun map(input: PlaylistsEntity): Playlist {
        return Playlist(
            name = input.playlistName,
            image = input.playlistImage,
            id = input.playlistId
        )
    }
}
