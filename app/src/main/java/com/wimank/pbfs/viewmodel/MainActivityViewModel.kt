package com.wimank.pbfs.viewmodel

import androidx.lifecycle.ViewModel
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

    private fun writeSession(session: Session) {
        viewModelScope.launch(context = Dispatchers.IO) {
            sessionUseCase.getRepository().insertSession(
                sessionUseCase.getMapper().map(session)
            )
        }
    }
}
