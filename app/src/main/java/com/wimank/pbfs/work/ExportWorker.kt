package com.wimank.pbfs.work

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wimank.pbfs.domain.usecase.PlaylistManager
import com.wimank.pbfs.domain.usecase.TracksManager
import com.wimank.pbfs.util.EMPTY_STRING
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class ExportWorker @WorkerInject constructor(
    private val tracksManager: TracksManager,
    private val playlistManager: PlaylistManager,
    private val workNotification: WorkNotification,
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            val job = async(Dispatchers.IO) {
                //Loading tracks notify
                workNotification.showLoadTracks()

                //Load tracks
                tracksManager.loadNetworkTracks()

                //Writing tracks notify
                workNotification.showWriteTracks()

                //Writing tracks in html file
                playlistManager.loadLocalPlaylists().forEach {
                    HtmlBuilder(it, tracksManager.loadLocalTracks(it.id), appContext).createFile()
                }
            }
            job.await()
            //Complete export notify
            workNotification.showExportComplete()
            Result.success()
        } catch (e: Exception) {
            //Error export notify
            workNotification.showExportError(e.message ?: EMPTY_STRING)
            Result.failure()
        }
    }
}
