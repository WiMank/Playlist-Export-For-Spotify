package com.wimank.pbfs.utils

import com.wimank.pbfs.domain.model.Session
import com.wimank.pbfs.rest.response.RefreshTokenResponse
import com.wimank.pbfs.room.entity.SessionEntity

object MockSession {

    fun getSessionEntity() = SessionEntity(
        sessionId = 0,
        accessToken = "asd",
        tokenType = "asd",
        expiresIn = 60000,
        refreshToken = "asd",
        scope = "asd"
    )

    fun getEmptySessionEntity() = SessionEntity(
        sessionId = 0,
        accessToken = "",
        tokenType = "",
        expiresIn = 0,
        refreshToken = "",
        scope = ""
    )

    fun getMaxExpiresInSessionEntity() = SessionEntity(
        sessionId = 0,
        accessToken = "token",
        tokenType = "asd",
        expiresIn = Long.MAX_VALUE,
        refreshToken = "asd",
        scope = "asd"
    )

    fun getSessionList(): List<Session> {
        return listOf(
            Session(
                sessionId = 0,
                accessToken = "at",
                tokenType = "t",
                expiresIn = 60000,
                refreshToken = "rt",
                scope = "asd"
            )
        )
    }

    fun getFlowSessionEntity(): List<SessionEntity> {
        return listOf(
            SessionEntity(
                sessionId = 0,
                accessToken = "at",
                tokenType = "t",
                expiresIn = 60000,
                refreshToken = "rt",
                scope = "asd"
            )
        )
    }

    fun getRefreshTokenResponse() = RefreshTokenResponse(
        accessToken = "asd",
        expiresIn = 45,
        refreshToken = "asd",
        scope = "asd",
        tokenType = "asd"
    )
}
