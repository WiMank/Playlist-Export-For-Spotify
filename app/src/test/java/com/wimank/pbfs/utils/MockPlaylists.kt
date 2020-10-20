package com.wimank.pbfs.utils

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.rest.response.NetworkPlaylists
import com.wimank.pbfs.room.entity.PlaylistsEntity

object MockPlaylists {

    fun getPlaylistsEntity(): List<PlaylistsEntity> {
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

    fun getPlaylist(): List<Playlist> {
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

    fun getNetworkPlaylist(): NetworkPlaylists {
        return NetworkPlaylists(
            href = "https://api.spotify.com/v1/users/wizzler/playlists",
            items = listOf(
                NetworkPlaylists.Item(
                    collaborative = false,
                    description = null,
                    externalUrls = NetworkPlaylists.Item.ExternalUrls(spotify = "http://open.spotify.com/user/wizzler/playlists/53Y8wT46QIMz5H4WQ8O22c"),
                    href = "https://api.spotify.com/v1/users/wizzler/playlists/53Y8wT46QIMz5H4WQ8O22c",
                    id = "53Y8wT46QIMz5H4WQ8O22c",
                    images = listOf(),
                    name = "Wizzlers Big Playlist",
                    owner = NetworkPlaylists.Item.Owner(
                        externalUrls = NetworkPlaylists.Item.Owner.ExternalUrls(
                            spotify = "http://open.spotify.com/user/wizzler"
                        ),
                        href = "https://api.spotify.com/v1/users/wizzler",
                        id = "wizzler",
                        type = "user",
                        uri = "spotify:user:wizzler"
                    ),
                    isPublic = true,
                    snapshotId = "bNLWdmhh+HDsbHzhckXeDC0uyKyg4FjPI/KEsKjAE526usnz2LxwgyBoMShVL+z+",
                    tracks = NetworkPlaylists.Item.Tracks(
                        "https://api.spotify.com/v1/users/wizzler/playlists/53Y8wT46QIMz5H4WQ8O22c/tracks",
                        30
                    ),
                    type = "playlist",
                    uri = "spotify:user:wizzler:playlist:53Y8wT46QIMz5H4WQ8O22c"
                )
            ),
            limit = 9,
            next = null,
            offset = 0,
            previous = null,
            total = 9
        )
    }
}
