package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.mapper.SessionMapper
import com.wimank.pbfs.repository.SessionRepository
import javax.inject.Inject

class SessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : UseCase<SessionRepository, SessionMapper> {

    override fun getRepository(): SessionRepository {
        return sessionRepository
    }

    override fun getMapper(): SessionMapper {
        return SessionMapper()
    }
}
