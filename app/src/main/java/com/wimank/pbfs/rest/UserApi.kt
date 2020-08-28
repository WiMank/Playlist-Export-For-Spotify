package com.wimank.pbfs.rest

import com.wimank.pbfs.rest.response.NetworkUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET("me")
    suspend fun getUserData(@Header("Authorization") token: String): Response<NetworkUser>

}
