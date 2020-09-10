package com.wimank.pbfs.rest

import com.wimank.pbfs.rest.response.NetworkTracks
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

/**
 * Web API endpoint where full details of the playlist’s tracks can be retrieved,
 * along with the total number of tracks in the playlist.
 */
interface TracksApi {

    @GET
    suspend fun loadTracks(
        @Header("Authorization") token: String,
        @Url tracks: String
    ): NetworkTracks

}
