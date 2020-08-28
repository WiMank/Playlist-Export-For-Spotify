package com.wimank.pbfs.rest.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUser(
    val country: String?,
    val display_name: String?,
    val email: String?,
    @Json(name = "external_urls")
    val externalUrls: ExternalUrls?,
    val followers: Followers?,
    val href: String?,
    val id: String?,
    val images: List<Image>?,
    val product: String?,
    val type: String?,
    val uri: String?
) {

    data class Image(
        val height: Any?,
        val url: String?,
        val width: Any?
    )

    data class Followers(
        val href: Any?,
        val total: Int?
    )

    data class ExternalUrls(
        val spotify: String?
    )
}