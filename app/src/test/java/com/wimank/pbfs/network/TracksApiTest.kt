package com.wimank.pbfs.network

import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.rest.TracksApi
import com.wimank.pbfs.utils.FileUtils
import com.wimank.pbfs.utils.MockTracksRepository.getNetworkTracks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.jupiter.api.Test
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class TracksApiTest : ApiMockWebServer() {

    @Test
    fun loadTracks() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(FileUtils.readTestResourceFile("tracks.json"))

        mockWebServer.enqueue(mockResponse)

        retrofit.create(TracksApi::class.java).loadTracks("token", "tracks_url").run {
            assertThat(this).isEqualTo(getNetworkTracks())
        }

        // confirm HTTP request for tracks
        mockWebServer.takeRequest().run {
            assertThat("/tracks_url").isEqualTo(path)
            assertThat(getHeader("Authorization")).isNotNull()
        }
    }
}
