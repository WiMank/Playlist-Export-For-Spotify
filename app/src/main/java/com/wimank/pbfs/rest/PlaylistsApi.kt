package com.wimank.pbfs.rest

import com.wimank.pbfs.rest.response.NetworkPlaylists
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PlaylistsApi {

    @GET("me/playlists")
    suspend fun loadPlaylistsList(
        @Header("Authorization: Bearer") token: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String,
    ): NetworkPlaylists

}
