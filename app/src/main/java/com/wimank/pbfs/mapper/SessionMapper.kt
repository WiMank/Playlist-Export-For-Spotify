package com.wimank.pbfs.mapper

import com.wimank.pbfs.domain.model.Session
import com.wimank.pbfs.room.entity.SessionEntity
import com.wimank.pbfs.util.SESSION_ID
import javax.inject.Inject

class SessionMapper : Mapper<Session, SessionEntity> {
    override fun map(input: Session): SessionEntity {
        return SessionEntity(
            sessionId = SESSION_ID,
            accessToken = input.accessToken,
            tokenType = input.tokenType,
            expiresIn = input.expiresIn,
            refreshToken = input.refreshToken,
            scope = input.scope
        )
    }
}

class SessionEntityMapper @Inject constructor() : Mapper<SessionEntity, Session> {
    override fun map(input: SessionEntity): Session {
        return Session(
            sessionId = SESSION_ID,
            accessToken = input.accessToken,
            tokenType = input.tokenType,
            expiresIn = input.expiresIn,
            refreshToken = input.refreshToken,
            scope = input.scope
        )
    }
}
