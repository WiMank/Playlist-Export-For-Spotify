package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.R
import com.wimank.pbfs.domain.model.Session
import com.wimank.pbfs.domain.usecase.SessionManager
import com.wimank.pbfs.mapper.SessionMapper
import com.wimank.pbfs.util.EMPTY_STRING
import com.wimank.pbfs.util.Event
import com.wimank.pbfs.util.SESSION_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.openid.appauth.TokenResponse
import timber.log.Timber

class AuthenticationViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    val authComplete = MutableLiveData(false)
    val authError = MutableLiveData<Event<Int>>()

    init {
        checkSession()
    }

    /**
     * Prepare and write session data to the database.
     */
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
            sessionManager.writeSession(SessionMapper().map(session))
        }
    }

    private fun checkSession() {
        viewModelScope.launch(context = Dispatchers.IO) {
            runCatching {
                sessionManager.flowSession()
            }.onSuccess { sessionFlow ->
                collectSession(sessionFlow)
            }.onFailure {
                authError.postValue(Event(R.string.authentication_error))
                Timber.e(it)
            }
        }
    }

    private suspend fun collectSession(sessionFlow: Flow<List<Session>>) {
        sessionFlow.collect {
            it.forEach { list ->
                authComplete.postValue(
                    list.accessToken.isNotEmpty() && (list.refreshToken.isNotEmpty())
                )
            }
        }
    }
}
