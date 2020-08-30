package com.wimank.pbfs.mapper

import com.wimank.pbfs.domain.model.Session
import com.wimank.pbfs.room.entity.SessionEntity
import com.wimank.pbfs.util.SESSION_ID

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
