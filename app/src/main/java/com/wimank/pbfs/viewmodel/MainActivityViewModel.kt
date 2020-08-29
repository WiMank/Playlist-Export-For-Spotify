package com.wimank.pbfs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.EMPTY_STRING
import com.wimank.pbfs.SESSION_ID
import com.wimank.pbfs.domain.model.Session
import com.wimank.pbfs.domain.usecase.SessionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.openid.appauth.TokenResponse
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(private val sessionUseCase: SessionUseCase) :
    ViewModel() {

    val sessionState = liveData(Dispatchers.IO) {
        emit(checkSessionState())
    }

    fun prepareAndWriteSession(tokenResponse: TokenResponse) {
        writeSession(tokenResponse.run {
            Session(
                SESSION_ID,
                accessToken ?: EMPTY_STRING,
                tokenType ?: EMPTY_STRING,
                accessTokenExpirationTime ?: 0L,
                refreshToken ?: EMPTY_STRING,
                scope ?: EMPTY_STRING
            )
        })
    }

    private suspend fun checkSessionState(): Boolean {
        return try {
            with(sessionUseCase.getRepository().getSession()) {
                accessToken.isNotEmpty() && (refreshToken.isNotEmpty())
            }
        } catch (e: Exception) {
            false
        }
    }

    private fun writeSession(session: Session) {
        viewModelScope.launch(context = Dispatchers.IO) {
            sessionUseCase.getRepository().run {
                if (this.hasSession()) {
                    updateSession(sessionUseCase.getMapper().map(session))
                } else {
                    insertSession(sessionUseCase.getMapper().map(session))
                }
            }
        }
    }
}
