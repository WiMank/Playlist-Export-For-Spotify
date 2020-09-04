package com.wimank.pbfs.domain.model

data class Track(
    val id: String,
    val url: String,
    val name: String,
    val artists: List<String>,
    val image: String
)
