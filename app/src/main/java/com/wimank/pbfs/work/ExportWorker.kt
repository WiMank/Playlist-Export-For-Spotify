package com.wimank.pbfs.work

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wimank.pbfs.domain.usecase.PlaylistManager
import com.wimank.pbfs.domain.usecase.TracksManager
import com.wimank.pbfs.util.APP_FOLDER
import com.wimank.pbfs.util.ARCHIVE_NAME
import com.wimank.pbfs.util.EMPTY_STRING
import com.wimank.pbfs.util.ZIP_APP_FOLDER
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.zeroturnaround.zip.ZipUtil
import java.io.File

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
                //loading tracks notify
                workNotification.showLoadTracks()
                delay(5000)

                //load tracks
                tracksManager.loadNetworkTracks()

                //writing tracks notify
                workNotification.showWriteTracks()

                //clear playlists folder and zip folder
                clearAppFolders()
                createAppFolders()

                //writing tracks in html file
                playlistManager.loadLocalPlaylists().forEach {
                    HtmlBuilder(it, tracksManager.loadLocalTracks(it.id), appContext).createFile()
                }

                //zip all html files
                ZipUtil.pack(
                    File(appContext.filesDir, APP_FOLDER),
                    File(appContext.filesDir, ZIP_APP_FOLDER + File.separator + ARCHIVE_NAME)
                )
            }
            job.await()
            //complete export notify
            workNotification.showExportComplete()
            Result.success()
        } catch (e: Exception) {
            //error export notify
            workNotification.showExportError(e.message ?: EMPTY_STRING)
            Result.failure()
        }
    }

    private fun clearAppFolders() {
        //clear playlists folder
        File(appContext.filesDir, APP_FOLDER).deleteRecursively()

        //clear zip folder
        File(appContext.filesDir, ZIP_APP_FOLDER).deleteRecursively()
    }

    private fun createAppFolders() {
        with(File(appContext.filesDir, APP_FOLDER)) {
            if (!exists()) {
                mkdir()
            }
        }

        with(File(appContext.filesDir, ZIP_APP_FOLDER)) {
            if (!exists()) {
                mkdir()
            }
        }
    }
}
