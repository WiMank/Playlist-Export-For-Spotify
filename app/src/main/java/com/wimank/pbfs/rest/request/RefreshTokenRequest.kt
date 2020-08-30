package com.wimank.pbfs.rest.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshTokenRequest(

    @Json(name = "grant_type")
    val grantType: String,

    @Json(name = "refresh_token")
    val refreshToken: String,

    @Json(name = "client_id")
    val clientId: String

)
