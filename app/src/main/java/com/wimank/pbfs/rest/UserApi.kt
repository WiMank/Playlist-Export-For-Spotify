package com.wimank.pbfs.rest

import com.wimank.pbfs.rest.response.NetworkUser
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Get detailed profile information about the current user (including the current user’s username).
 */
interface UserApi {

    @GET("me")
    suspend fun getUserData(@Header("Authorization") token: String): NetworkUser

}
