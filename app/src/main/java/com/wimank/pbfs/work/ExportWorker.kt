package com.wimank.pbfs.work

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wimank.pbfs.domain.usecase.PlaylistManager
import com.wimank.pbfs.domain.usecase.TracksManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class ExportWorker @WorkerInject constructor(
    private val tracksManager: TracksManager,
    private val playlistManager: PlaylistManager,
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        val job = async(Dispatchers.IO) {
            tracksManager.loadNetworkTracks()
            val lp = playlistManager.loadLocalPlaylists()
            lp.forEach {
                HtmlBuilder(it, tracksManager.loadLocalTracks(it.id), appContext).createFile()
            }
        }

        job.await()
        Result.success()
    }
}
