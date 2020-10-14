package com.wimank.pbfs.room

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.room.dao.TracksDao
import com.wimank.pbfs.room.entity.PlaylistsEntity
import com.wimank.pbfs.room.entity.TracksEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TracksDaoTest : DataBaseTest() {

    private val tracksDao: TracksDao by lazy { db.getTracksDao() }
    private val playlistDao: PlaylistDao by lazy { db.getPlaylistDao() }

    private val tracksEntity = TracksEntity(
        trackId = "0",
        playlistId = "0",
        playlistName = "p_name",
        url = "url",
        name = "name",
        artists = "artists",
        image = "image"
    )

    private val playlistEntity = PlaylistsEntity(
        playlistId = "0",
        playlistName = "playlistName",
        playlistImage = "playlistImage",
        tracksUrl = "tracksUrl",
        tracksCount = 1,
        isPublic = false,
        isCollaborative = false
    )

    @Before
    fun setup() = runBlocking {
        // The playlist entity is needed because of the table relationship
        playlistDao.insert(playlistEntity)
        tracksDao.insert(listOf(tracksEntity))
    }

    @Test
    fun insert() = runBlocking {
        assertThat(tracksDao.getTracks("0")).isNotEmpty()
    }

    @Test
    fun getTracks() = runBlocking {
        assertThat(tracksDao.getTracks("0")).isNotEmpty()
    }

    @Test
    fun clearTracks() = runBlocking {
        tracksDao.clearTracks()
        assertThat(tracksDao.getTracks("0")).isEmpty()
    }
}
