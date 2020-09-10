package com.wimank.pbfs.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.FileProvider
import timber.log.Timber
import java.io.File

/**
 * Archiving html playlist files.
 */
class ShareZipArchive(private val context: Context) {

    fun share() {
        val fileUri: Uri? = try {
            FileProvider.getUriForFile(context, FILE_PROVIDER, prepareFile())
        } catch (e: IllegalArgumentException) {
            Timber.e("The selected file can't be shared: ${prepareFile()}")
            null
        }
        startSend(fileUri)
    }

    fun zipFileExist() =
        File(context.filesDir.path + File.separator + ZIP_APP_FOLDER, ARCHIVE_NAME).exists()

    private fun prepareFile(): File {
        return File(context.filesDir.path + File.separator + ZIP_APP_FOLDER, ARCHIVE_NAME)
    }

    private fun startSend(fileUri: Uri?) {
        fileUri?.let {
            val intent = Intent(Intent.ACTION_SEND).apply {
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                setDataAndType(fileUri, ZIP_MIME_TYPE)
                putExtra(Intent.EXTRA_STREAM, fileUri)
            }

            val pm: PackageManager = context.packageManager
            if (intent.resolveActivity(pm) != null) {
                context.startActivity(intent)
            }
        }
    }
}
