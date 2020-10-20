package com.wimank.pbfs.network

import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.rest.PlaylistsApi
import com.wimank.pbfs.utils.FileUtils
import com.wimank.pbfs.utils.MockPlaylists.getNetworkPlaylist
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.jupiter.api.Test
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class PlaylistsApiTest : ApiMockWebServer() {

    private val mockResponse = MockResponse()
        .setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(FileUtils.readTestResourceFile("playlists.json"))

    private val playlistsApi: PlaylistsApi by lazy { retrofit.create(PlaylistsApi::class.java) }

    @Test
    fun loadPlaylists() = runBlocking {
        mockWebServer.enqueue(mockResponse)

        playlistsApi.loadPlaylists("token", 50, 0).run {
            assertThat(this).isEqualTo(getNetworkPlaylist())
        }

        // confirm HTTP request for tracks
        mockWebServer.takeRequest().run {
            assertThat("/me/playlists?limit=50&offset=0").isEqualTo(path)
            assertThat(getHeader("Authorization")).isNotNull()
        }
    }

    @Test
    fun loadNextPlaylists() = runBlocking {
        mockWebServer.enqueue(mockResponse)

        playlistsApi.loadNextPlaylists("token", "next_playlist").run {
            assertThat(this).isEqualTo(getNetworkPlaylist())
        }

        // confirm HTTP request for playlists
        mockWebServer.takeRequest().run {
            assertThat("/next_playlist").isEqualTo(path)
            assertThat(getHeader("Authorization")).isNotNull()
        }
    }
}
