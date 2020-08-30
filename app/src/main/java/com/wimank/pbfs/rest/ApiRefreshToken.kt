package com.wimank.pbfs.rest

import com.wimank.pbfs.rest.request.RefreshTokenRequest
import com.wimank.pbfs.rest.response.RefreshTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiRefreshToken {

    @POST("api/token")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse

}
