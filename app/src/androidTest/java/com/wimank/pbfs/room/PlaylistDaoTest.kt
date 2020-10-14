package com.wimank.pbfs.room

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.room.entity.PlaylistsEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PlaylistDaoTest : DataBaseTest() {

    private val playlistDao: PlaylistDao by lazy { db.getPlaylistDao() }
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
    fun setUp() = runBlocking {
        playlistDao.insert(playlistEntity)
    }

    @Test
    fun flowPlaylists() = runBlocking {
        assertThat(playlistDao.flowPlaylists().first()).isEqualTo(listOf(playlistEntity))
    }

    @Test
    fun getPlaylists() = runBlocking {
        assertThat(playlistDao.getPlaylists()).isNotEmpty()
    }

    @Test
    fun insert() = runBlocking {
        playlistDao.clearPlaylists()
        playlistDao.insert(playlistEntity)
        assertThat(playlistDao.getPlaylists().first()).isEqualTo(playlistEntity)
    }

    @Test
    fun clearPlaylists() = runBlocking {
        playlistDao.clearPlaylists()
        assertThat(playlistDao.getPlaylists()).isEmpty()
    }

    @Test
    fun clearAndInsertPlaylists() = runBlocking {
        playlistDao.clearAndInsertPlaylists(listOf(playlistEntity))
        assertThat(playlistDao.getPlaylists()).isNotEmpty()
    }
}
