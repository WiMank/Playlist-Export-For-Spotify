package com.wimank.pbfs.rest.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkPlaylists(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int,
) {
    @JsonClass(generateAdapter = true)
    data class Item(
        val collaborative: Boolean,
        val description: String?,
        @Json(name = "external_urls")
        val externalUrls: ExternalUrls,
        val href: String,
        val id: String,
        val images: List<PlaylistImage>?,
        val name: String,
        val owner: Owner,
        @Json(name = "public")
        val isPublic: Boolean?,
        @Json(name = "snapshot_id")
        val snapshotId: String,
        val tracks: Tracks?,
        val type: String,
        val uri: String,
    ) {
        @JsonClass(generateAdapter = true)
        data class ExternalUrls(
            val spotify: String,
        )

        @JsonClass(generateAdapter = true)
        data class PlaylistImage(
            val height: Any?,
            val url: String?,
            val width: Any?,
        )

        @JsonClass(generateAdapter = true)
        data class Owner(
            @Json(name = "external_urls")
            val externalUrls: ExternalUrls,
            val href: String,
            val id: String,
            val type: String,
            val uri: String,
        ) {
            @JsonClass(generateAdapter = true)
            data class ExternalUrls(
                val spotify: String,
            )
        }

        @JsonClass(generateAdapter = true)
        data class Tracks(
            val href: String?,
            val total: Int?,
        )
    }
}
