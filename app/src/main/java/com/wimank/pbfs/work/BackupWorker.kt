package com.wimank.pbfs.work

import android.content.Context
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wimank.pbfs.domain.usecase.SessionManager
import com.wimank.pbfs.repository.TracksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class BackupWorker @WorkerInject constructor(
    private val tracksRepository: TracksRepository,
    private val sessionManager: SessionManager,
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        val job = async(Dispatchers.IO) {

        }

        job.await()
        Result.success()
    }
}
