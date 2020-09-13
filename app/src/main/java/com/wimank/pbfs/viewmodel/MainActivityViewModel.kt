package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import com.wimank.pbfs.domain.model.Session
import com.wimank.pbfs.domain.usecase.SessionManager
import com.wimank.pbfs.mapper.SessionMapper
import com.wimank.pbfs.util.EMPTY_STRING
import com.wimank.pbfs.util.NetworkManager
import com.wimank.pbfs.util.SESSION_ID
import com.wimank.pbfs.work.ExportWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.openid.appauth.TokenResponse

class MainActivityViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager,
    private val networkManager: NetworkManager
) : ViewModel() {

    val sessionState = liveData(Dispatchers.IO) {
        emit(checkSessionState())
    }

    val networkState = liveData(Dispatchers.Default) {
        emit(networkManager.isNetworkAvailable())
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

    /**
     * Create a task to export playlists.
     */
    fun createExportWork(): OneTimeWorkRequest {
        return OneTimeWorkRequestBuilder<ExportWorker>().apply {
            setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
        }.build()
    }

    private suspend fun checkSessionState(): Boolean {
        return try {
            sessionManager.hasSession()
        } catch (e: Exception) {
            false
        }
    }

    private fun writeSession(session: Session) {
        viewModelScope.launch(context = Dispatchers.IO) {
            sessionManager.writeSession(SessionMapper().map(session))
        }
    }
}
