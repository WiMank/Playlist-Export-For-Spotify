package com.wimank.pbfs.rest.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUser(
    val country: String?,
    @Json(name = "display_name")
    val displayName: String?,
    val email: String,
    @Json(name = "external_urls")
    val externalUrls: ExternalUrls,
    val followers: Followers?,
    val href: String,
    val id: String,
    val images: List<Image>?,
    val product: String?,
    val type: String,
    val uri: String,
) {
    @JsonClass(generateAdapter = true)
    data class Image(
        val height: Int?,
        val url: String?,
        val width: Int?,
    )

    @JsonClass(generateAdapter = true)
    data class Followers(
        val href: Any?,
        val total: Int?,
    )

    @JsonClass(generateAdapter = true)
    data class ExternalUrls(
        val spotify: String?,
    )
}
