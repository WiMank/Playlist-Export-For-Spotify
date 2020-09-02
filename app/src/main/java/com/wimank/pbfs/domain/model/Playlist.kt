package com.wimank.pbfs.domain.model

data class Playlist(
    val name: String,
    val image: String,
    val id: String,
    val isPublic: Boolean,
    val isCollaborative: Boolean
)
