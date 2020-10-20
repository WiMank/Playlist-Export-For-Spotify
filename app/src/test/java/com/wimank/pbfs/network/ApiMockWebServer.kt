package com.wimank.pbfs.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.jupiter.api.BeforeEach
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
open class ApiMockWebServer {

    internal var mockWebServer = MockWebServer()
    internal lateinit var retrofit: Retrofit

    @BeforeEach
    fun setup() {
        mockWebServer.start()
        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}
