package com.wimank.pbfs.usecase

import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.domain.usecase.SessionManager
import com.wimank.pbfs.domain.usecase.UserManager
import com.wimank.pbfs.repository.UserRepository
import com.wimank.pbfs.utils.CoroutineRule
import com.wimank.pbfs.utils.MockUser.getUser
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class UserManagerTest {

    private val sessionManager: SessionManager = mockk()
    private val userRepository: UserRepository = mockk()
    private val userManager: UserManager = UserManager(sessionManager, userRepository)

    @get:Rule
    var coroutinesRule = CoroutineRule()

    companion object {
        private const val TOKEN = "token"
    }

    @Test
    fun loadUser() = runBlockingTest {
        coEvery { userRepository.loadNetworkUser(any()) } just Runs
        coEvery { userRepository.canUpdateProfile() } returns true
        coEvery { sessionManager.checkSessionBeforeRequest() } returns TOKEN
        coEvery { userRepository.flowUser() } returns flowOf(getUser())

        assertThat(userManager.loadUser().first()).isEqualTo(getUser())

        coVerifyAll {
            userRepository.loadNetworkUser(any())
            userRepository.canUpdateProfile()
            sessionManager.checkSessionBeforeRequest()
            userRepository.flowUser()
        }
    }
}
