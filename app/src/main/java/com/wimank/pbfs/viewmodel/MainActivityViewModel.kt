package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import com.wimank.pbfs.domain.usecase.SessionManager
import com.wimank.pbfs.util.NetworkManager
import com.wimank.pbfs.work.ExportWorker
import kotlinx.coroutines.Dispatchers

class MainActivityViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager,
    private val networkManager: NetworkManager
) : ViewModel() {

    val sessionState = liveData<Boolean>(Dispatchers.IO) {
        runCatching {
            sessionManager.hasSession()
        }.onSuccess { hasSession ->
            emit(hasSession)
        }.onFailure {
            emit(false)
        }
    }

    val networkState = liveData(Dispatchers.Default) {
        emit(networkManager.isNetworkAvailable())
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
}
