package com.wimank.pbfs.usecase

import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.domain.usecase.SessionManager
import com.wimank.pbfs.repository.SessionRepository
import com.wimank.pbfs.util.EMPTY_STRING
import com.wimank.pbfs.utils.CoroutineRule
import com.wimank.pbfs.utils.MockSession.getEmptySessionEntity
import com.wimank.pbfs.utils.MockSession.getMaxExpiresInSessionEntity
import com.wimank.pbfs.utils.MockSession.getSessionEntity
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class SessionManagerTest {

    private val sessionRepository: SessionRepository = mockk()
    private val sessionManager = SessionManager(sessionRepository)

    @get:Rule
    var coroutinesRule = CoroutineRule()

    @Test
    fun hasSession() = runBlockingTest {
        coEvery { sessionRepository.getSession() } returns getSessionEntity()
        assertThat(sessionManager.hasSession()).isTrue()

        coEvery { sessionRepository.getSession() } returns getEmptySessionEntity()
        assertThat(sessionManager.hasSession()).isFalse()

        coVerify { sessionRepository.getSession() }
    }

    @Test
    fun checkSessionBeforeRequest() = runBlockingTest {
        // Receive new token
        coEvery { sessionRepository.getSession() } returns getSessionEntity()
        coEvery { sessionRepository.hasSession() } returns true
        coEvery { sessionRepository.refreshToken() } returns getSessionEntity()
        coEvery { sessionRepository.updateSession(any()) } just Runs
        assertThat(sessionManager.checkSessionBeforeRequest()).isEqualTo(getSessionEntity().accessToken)

        // Receive local token
        coEvery { sessionRepository.getSession() } returns getMaxExpiresInSessionEntity()
        assertThat(sessionManager.checkSessionBeforeRequest()).isEqualTo(
            getMaxExpiresInSessionEntity().accessToken
        )

        // Session absent
        coEvery { sessionRepository.hasSession() } returns false
        assertThat(sessionManager.checkSessionBeforeRequest()).isEqualTo(EMPTY_STRING)

        coVerifyAll {
            sessionRepository.getSession()
            sessionRepository.hasSession()
            sessionRepository.refreshToken()
            sessionRepository.updateSession(any())
        }
    }
}
