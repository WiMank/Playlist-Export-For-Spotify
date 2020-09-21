package com.wimank.pbfs.repository

import com.wimank.pbfs.mapper.NetworkPlaylistMapper
import com.wimank.pbfs.mapper.PlaylistMapper
import com.wimank.pbfs.rest.PlaylistsApi
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.utils.CoroutineRule
import com.wimank.pbfs.utils.MockUtil.mockPlaylist
import com.wimank.pbfs.utils.MockUtil.mockPlaylistsEntity
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class PlaylistsRepositoryImplTest {
    private val playlistDao: PlaylistDao = mockk()
    private val playlistsApi: PlaylistsApi = mockk()
    private val playlistMapper = PlaylistMapper()
    private val networkPlaylistMapper = NetworkPlaylistMapper()
    private lateinit var playlistsRepository: PlaylistsRepositoryImpl

    @get:Rule
    var coroutinesRule = CoroutineRule()

    @BeforeEach
    fun setUp() {
        playlistsRepository =
            PlaylistsRepositoryImpl(
                playlistDao,
                playlistsApi,
                playlistMapper,
                networkPlaylistMapper
            )
    }

    @Test
    fun loadNetworkPlaylists() = runBlockingTest {
        val mockPlaylistsEntity = mockPlaylistsEntity()
        val mockPlaylist = mockPlaylist()

        every { playlistDao.flowPlaylists() } returns flowOf(mockPlaylistsEntity)
        playlistsRepository.flowingPlaylists().collect {
            Assert.assertEquals(mockPlaylist, it)
        }
        verify { playlistDao.flowPlaylists() }
    }

    @Test
    fun loadLocalPlaylists() = runBlockingTest {
        coEvery { playlistDao.getPlaylists() } returns mockPlaylistsEntity()
        Assert.assertEquals(mockPlaylist(), playlistsRepository.loadLocalPlaylists())
        coVerify { playlistDao.getPlaylists() }
    }
}
