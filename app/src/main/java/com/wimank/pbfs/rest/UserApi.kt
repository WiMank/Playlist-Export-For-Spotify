package com.wimank.pbfs.rest

import com.wimank.pbfs.rest.request.NetworkUser
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET("me")
    suspend fun getUserData(@Header("Authorization: Bearer") token: String): NetworkUser

}
