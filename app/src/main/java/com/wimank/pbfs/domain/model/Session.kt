package com.wimank.pbfs.domain.model

data class Session(
    val sessionId: Long,
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Long,
    val refreshToken: String,
    val scope: String
)
