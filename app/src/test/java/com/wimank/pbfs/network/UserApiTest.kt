package com.wimank.pbfs.network

import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.rest.UserApi
import com.wimank.pbfs.utils.FileUtils.readTestResourceFile
import com.wimank.pbfs.utils.MockUserRepository.getNetworkUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import java.net.HttpURLConnection


@ExperimentalCoroutinesApi
class UserApiTest : ApiMockWebServer() {

    @Test
    fun getUserData() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(readTestResourceFile("user_profile.json"))

        mockWebServer.enqueue(mockResponse)

        retrofit.create(UserApi::class.java).getUserData("token").run {
            assertThat(this).isEqualTo(getNetworkUser())
        }

        // confirm HTTP request for user profile
        mockWebServer.takeRequest().run {
            assertThat("/me").isEqualTo(path)
            assertThat(getHeader("Authorization")).isNotNull()
        }
    }
}
