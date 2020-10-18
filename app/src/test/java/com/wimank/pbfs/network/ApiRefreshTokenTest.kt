package com.wimank.pbfs.network

import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.rest.ApiRefreshToken
import com.wimank.pbfs.rest.response.RefreshTokenResponse
import com.wimank.pbfs.utils.FileUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class ApiRefreshTokenTest : ApiMockWebServer() {

    @Test
    fun refreshTokens() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(FileUtils.readTestResourceFile("refresh_token.json"))

        mockWebServer.enqueue(mockResponse)

        retrofit.create(ApiRefreshToken::class.java).refreshTokens("gt", "rt", "cid").run {
            assertThat(this).isEqualTo(
                RefreshTokenResponse(
                    accessToken = "NgCXRK...MzYjw",
                    expiresIn = 3600,
                    refreshToken = "NgAagA...Um_SHo",
                    scope = "user-read-private user-read-email",
                    tokenType = "Bearer"
                )
            )
        }

        // confirm HTTP request for refresh token
        mockWebServer.takeRequest().run {
            assertThat("/api/token").isEqualTo(path)
        }
    }
}
