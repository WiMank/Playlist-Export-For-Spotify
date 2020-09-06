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
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        val job = async(Dispatchers.IO) {

            //val np = playlistManager.loadNetworkPlaylists()
            //val nt = tracksManager.loadNetworkTracks()

            //val lp = playlistManager.loadLocalPlaylists()
            //val lt = tracksManager.loadLocalTracks(lp[0].id)

        }

        job.await()
        Result.success()
    }
}
