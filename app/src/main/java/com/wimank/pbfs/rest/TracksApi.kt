package com.wimank.pbfs.rest

import com.wimank.pbfs.rest.response.NetworkTracks
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface TracksApi {

    @GET
    suspend fun loadTracks(
        @Header("Authorization") token: String,
        @Url tracks: String
    ): NetworkTracks

}
