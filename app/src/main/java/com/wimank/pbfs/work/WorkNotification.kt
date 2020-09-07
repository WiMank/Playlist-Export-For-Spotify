package com.wimank.pbfs.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.wimank.pbfs.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WorkNotification @Inject constructor(@ApplicationContext private val context: Context) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private var builder = NotificationCompat.Builder(context, CHANNEL_ID)

    private companion object {
        const val CHANNEL_ID = "Playlists export"
        const val LOADING_TRACKS = 0
        const val ERROR_LOADING_TRACKS = 0
        const val COMPLETE = 0
    }

    init {
        createNotificationChannel()
    }

    fun showLoadTracks() {
        notificationManager.notify(
            LOADING_TRACKS,
            builder.apply {
                setSmallIcon(R.drawable.ic_cloud_download_24px)
                setContentTitle(context.getString(R.string.loading_tracks))
                setProgress(0, 0, true)
                priority = NotificationCompat.PRIORITY_DEFAULT
            }.build()
        )
    }

    fun showWriteTracks() {
        notificationManager.notify(
            LOADING_TRACKS,
            builder.apply {
                setSmallIcon(R.drawable.ic_write)
                setContentTitle(context.getString(R.string.write_playlists))
                setProgress(0, 0, true)
                priority = NotificationCompat.PRIORITY_DEFAULT
            }.build()
        )
    }

    fun showExportComplete() {
        notificationManager.notify(
            COMPLETE,
            builder.apply {
                setSmallIcon(R.drawable.ic_ok)
                setContentTitle(context.getString(R.string.export_complete))
                setProgress(0, 0, false)
                priority = NotificationCompat.PRIORITY_DEFAULT
            }.build()
        )
    }

    fun showExportError(cause: String) {
        notificationManager.notify(
            COMPLETE,
            builder.apply {
                setSmallIcon(R.drawable.ic_error)
                setContentTitle(context.getString(R.string.export_error))
                setProgress(0, 0, false)
                setStyle(NotificationCompat.BigTextStyle().bigText(cause))
                priority = NotificationCompat.PRIORITY_DEFAULT
            }.build()
        )
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                enableLights(false)
                enableVibration(false)
                setSound(null, null)
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
    }
}
