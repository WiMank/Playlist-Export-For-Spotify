package com.wimank.pbfs.rest.response

import com.squareup.moshi.Json

data class NetworkPlaylists(
    val href: String?,
    val items: List<Item?>?,
    val limit: Int?,
    val next: Any?,
    val offset: Int?,
    val previous: Any?,
    val total: Int?
) {
    data class Item(
        val collaborative: Boolean?,
        @Json(name = "external_urls")
        val externalUrls: ExternalUrls?,
        val href: String?,
        val id: String?,
        val images: List<Any?>?,
        val name: String?,
        val owner: Owner?,
        @Json(name = "public")
        val isPublic: Boolean?,
        @Json(name = "snapshot_id")
        val snapshotId: String?,
        val tracks: Tracks?,
        val type: String?,
        val uri: String?
    ) {
        data class ExternalUrls(
            val spotify: String?
        )

        data class Owner(
            @Json(name = "external_urls")
            val externalUrls: ExternalUrls?,
            val href: String?,
            val id: String?,
            val type: String?,
            val uri: String?
        ) {
            data class ExternalUrls(
                val spotify: String?
            )
        }

        data class Tracks(
            val href: String?,
            val total: Int?
        )
    }
}
