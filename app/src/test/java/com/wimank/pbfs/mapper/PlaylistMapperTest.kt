package com.wimank.pbfs.mapper

import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.util.EMPTY_STRING
import com.wimank.pbfs.utils.MockPlaylists.getNetworkPlaylist
import com.wimank.pbfs.utils.MockPlaylists.getPlaylistsEntity
import org.junit.Test

class PlaylistMapperTest {

    private val playlistMapper = PlaylistMapper()
    private val networkPlaylistMapper = NetworkPlaylistMapper()

    @Test
    fun playlistMap() {
        val playlistsEntity = getPlaylistsEntity().first()
        val mapResult = playlistMapper.map(playlistsEntity)

        assertThat(playlistsEntity.playlistId).isEqualTo(mapResult.id)
        assertThat(playlistsEntity.playlistName).isEqualTo(mapResult.name)
        assertThat(playlistsEntity.isCollaborative).isEqualTo(mapResult.isCollaborative)
        assertThat(playlistsEntity.isPublic).isEqualTo(mapResult.isPublic)
        assertThat(playlistsEntity.playlistImage).isEqualTo(mapResult.image)
        assertThat(playlistsEntity.tracksCount).isEqualTo(mapResult.tracksCount)
    }

    @Test
    fun networkPlaylistMap() {
        val networkPlaylist = getNetworkPlaylist()
        val mapResult = networkPlaylistMapper.map(listOf(networkPlaylist)).first()

        assertThat(networkPlaylist.items.first().id).isEqualTo(mapResult.playlistId)
        assertThat(networkPlaylist.items.first().name).isEqualTo(mapResult.playlistName)
        assertThat(networkPlaylist.items.first().collaborative).isEqualTo(mapResult.isCollaborative)
        assertThat(networkPlaylist.items.first().isPublic).isEqualTo(mapResult.isPublic)
        assertThat(EMPTY_STRING).isEqualTo(mapResult.playlistImage)
        assertThat(networkPlaylist.items.first().tracks?.total).isEqualTo(mapResult.tracksCount)
        assertThat(networkPlaylist.items.first().tracks?.href).isEqualTo(mapResult.tracksUrl)
    }
}
