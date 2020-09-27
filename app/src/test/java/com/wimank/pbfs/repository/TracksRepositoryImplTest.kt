package com.wimank.pbfs.repository

import com.wimank.pbfs.mapper.NetworkTracksMapper
import com.wimank.pbfs.mapper.TracksMapper
import com.wimank.pbfs.rest.TracksApi
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.room.dao.TracksDao
import com.wimank.pbfs.util.EMPTY_STRING
import com.wimank.pbfs.utils.CoroutineRule
import com.wimank.pbfs.utils.MockPlaylistsRepository.getPlaylistsEntity
import com.wimank.pbfs.utils.MockTracksRepository.getNetworkTracks
import com.wimank.pbfs.utils.MockTracksRepository.getTracks
import com.wimank.pbfs.utils.MockTracksRepository.getTracksEntities
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class TracksRepositoryImplTest {

    private val tracksApi: TracksApi = mockk()
    private val tracksDao: TracksDao = mockk()
    private val playlistDao: PlaylistDao = mockk()
    private val networkTracksMapper = NetworkTracksMapper()
    private val tracksMapper = TracksMapper()
    private lateinit var tracksRepositoryImpl: TracksRepositoryImpl

    @get:Rule
    var coroutinesRule = CoroutineRule()

    @BeforeEach
    fun setUp() {
        tracksRepositoryImpl = TracksRepositoryImpl(
            tracksApi,
            tracksDao,
            playlistDao,
            networkTracksMapper,
            tracksMapper
        )
    }

    @Test
    fun loadNetworkTracks() = runBlockingTest {
        coEvery { tracksDao.clearTracks() } just Runs
        coEvery { tracksDao.insert(listOf()) } just Runs
        coEvery { playlistDao.getPlaylists() } returns getPlaylistsEntity()
        coEvery { tracksApi.loadTracks(any(), any()) } returns getNetworkTracks()

        tracksRepositoryImpl.loadNetworkTracks(token = "", limit = 0, offset = 0)

        coVerifyAll {
            tracksDao.clearTracks()
            tracksDao.insert(listOf())
            playlistDao.getPlaylists()
            tracksApi.loadTracks(any(), any())
        }
    }

    @Test
    fun loadLocalTracks() = runBlockingTest {
        coEvery { tracksDao.getTracks(any()) } returns getTracksEntities()

        Assert.assertEquals(getTracks(), tracksRepositoryImpl.loadLocalTracks(EMPTY_STRING))

        coVerify { tracksDao.getTracks(any()) }
    }
}
