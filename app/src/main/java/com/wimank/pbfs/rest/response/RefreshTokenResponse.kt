package com.wimank.pbfs.rest.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshTokenResponse(

    @Json(name = "access_token")
    val accessToken: String,

    @Json(name = "expires_in")
    val expiresIn: Int,

    @Json(name = "refresh_token")
    val refreshToken: String,

    val scope: String,

    @Json(name = "token_type")
    val tokenType: String

)
