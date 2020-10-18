package com.wimank.pbfs.network

import com.google.common.truth.Truth
import com.wimank.pbfs.rest.PlaylistsApi
import com.wimank.pbfs.utils.FileUtils
import com.wimank.pbfs.utils.MockPlaylistsRepository.getNetworkPlaylist
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class PlaylistsApiTest : ApiMockWebServer() {

    private val mockResponse = MockResponse()
        .setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(FileUtils.readTestResourceFile("playlists.json"))

    @Test
    fun loadPlaylists() = runBlocking {
        mockWebServer.enqueue(mockResponse)

        retrofit.create(PlaylistsApi::class.java).loadPlaylists("token", 50, 0).run {
            Truth.assertThat(this).isEqualTo(getNetworkPlaylist())
        }

        // confirm HTTP request for tracks
        mockWebServer.takeRequest().run {
            Truth.assertThat("/me/playlists?limit=50&offset=0").isEqualTo(path)
            Truth.assertThat(getHeader("Authorization")).isNotNull()
        }
    }

    @Test
    fun loadNextPlaylists() = runBlocking {
        mockWebServer.enqueue(mockResponse)

        retrofit.create(PlaylistsApi::class.java).loadNextPlaylists("token", "next_playlist").run {
            Truth.assertThat(this).isEqualTo(getNetworkPlaylist())
        }

        // confirm HTTP request for tracks
        mockWebServer.takeRequest().run {
            Truth.assertThat("/next_playlist").isEqualTo(path)
            Truth.assertThat(getHeader("Authorization")).isNotNull()
        }
    }
}
