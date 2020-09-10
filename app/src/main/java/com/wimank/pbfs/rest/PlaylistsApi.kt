package com.wimank.pbfs.rest

import com.wimank.pbfs.rest.response.NetworkPlaylists
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Method [loadPlaylists] get a List of Current User's Playlists.
 * Method [loadNextPlaylists] is required for paginated loading.
 */
interface PlaylistsApi {

    @GET("me/playlists")
    suspend fun loadPlaylists(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): NetworkPlaylists

    @GET
    suspend fun loadNextPlaylists(
        @Header("Authorization") token: String,
        @Url nextPlaylistUrl: String
    ): NetworkPlaylists

}
