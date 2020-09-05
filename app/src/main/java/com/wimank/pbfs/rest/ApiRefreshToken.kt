package com.wimank.pbfs.rest

import com.wimank.pbfs.rest.response.RefreshTokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiRefreshToken {

    @POST("api/token")
    @FormUrlEncoded
    suspend fun refreshToken(
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refresh_token: String,
        @Field("client_id") client_id: String
    ): RefreshTokenResponse

}