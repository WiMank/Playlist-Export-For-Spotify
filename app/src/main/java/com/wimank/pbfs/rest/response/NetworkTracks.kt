package com.wimank.pbfs.rest.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkTracks(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
) {
    @JsonClass(generateAdapter = true)
    data class Item(
        @Json(name = "added_at")
        val addedAt: String?,
        @Json(name = "added_by")
        val addedBy: AddedBy?,
        @Json(name = "is_local")
        val isLocal: Boolean,
        val track: Track,
    ) {
        @JsonClass(generateAdapter = true)
        data class AddedBy(
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
        data class Track(
            val album: Album,
            val artists: List<Artist>,
            val available_markets: List<String>,
            @Json(name = "disc_number")
            val discNumber: Int,
            @Json(name = "duration_ms")
            val durationMs: Int,
            val explicit: Boolean,
            @Json(name = "external_ids")
            val externalIds: ExternalIds,
            @Json(name = "external_urls")
            val externalUrls: ExternalUrls,
            val href: String,
            val id: String,
            val name: String,
            val popularity: Int,
            @Json(name = "preview_url")
            val previewUrl: String?,
            @Json(name = "track_number")
            val trackNumber: Int,
            val type: String,
            val uri: String,
        ) {
            @JsonClass(generateAdapter = true)
            data class Album(
                val album_type: String,
                val artists: List<Artist>,
                @Json(name = "available_markets")
                val availableMarkets: List<String>,
                @Json(name = "external_urls")
                val externalUrls: ExternalUrls,
                val href: String,
                val id: String,
                val images: List<Image>?,
                val name: String,
                val type: String,
                val uri: String,
            ) {
                @JsonClass(generateAdapter = true)
                data class Artist(
                    @Json(name = "external_urls")
                    val externalUrls: ExternalUrls,
                    val href: String,
                    val id: String,
                    val name: String,
                    val type: String,
                    val uri: String,
                ) {
                    @JsonClass(generateAdapter = true)
                    data class ExternalUrls(
                        val spotify: String,
                    )
                }

                @JsonClass(generateAdapter = true)
                data class ExternalUrls(
                    val spotify: String,
                )

                @JsonClass(generateAdapter = true)
                data class Image(
                    val height: Int,
                    val url: String,
                    val width: Int,
                )
            }

            @JsonClass(generateAdapter = true)
            data class Artist(
                @Json(name = "external_urls")
                val externalUrls: ExternalUrls,
                val href: String,
                val id: String,
                val name: String,
                val type: String,
                val uri: String,
            ) {
                @JsonClass(generateAdapter = true)
                data class ExternalUrls(
                    val spotify: String,
                )
            }

            @JsonClass(generateAdapter = true)
            data class ExternalIds(
                val isrc: String,
            )

            @JsonClass(generateAdapter = true)
            data class ExternalUrls(
                val spotify: String,
            )
        }
    }
}
