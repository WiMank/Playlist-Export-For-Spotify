package com.wimank.pbfs.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.wimank.pbfs.R
import com.wimank.pbfs.util.EMPTY_STRING
import com.wimank.pbfs.util.KEY_CANCEL
import com.wimank.pbfs.util.VALUE_CANCEL
import com.wimank.pbfs.util.clearActions
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * Operation status notifications for [ExportWorker].
 */
class WorkNotification @Inject constructor(@ApplicationContext private val context: Context) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private var builder = NotificationCompat.Builder(context, CHANNEL_ID)

    private val cancelIntent = Intent(context, CancelWorkBroadcastReceiver::class.java).apply {
        action = ACTION_CANCEL
        putExtra(KEY_CANCEL, VALUE_CANCEL)
    }

    private val cancelPendingIntent = PendingIntent.getBroadcast(context, 0, cancelIntent, 0)

    init {
        createNotificationChannel()
    }

    fun showLoadTracks() {
        notificationManager.notify(
            NOTIFICATION_ID,
            builder.apply {
                setSmallIcon(R.drawable.ic_cloud_download_24px)
                setContentTitle(context.getString(R.string.loading_tracks))
                setProgress(0, 0, true)
                priority = NotificationCompat.PRIORITY_DEFAULT
                addAction(
                    R.drawable.ic_close_24px,
                    context.getString(R.string.cancel),
                    cancelPendingIntent
                )
            }.build()
        )
    }

    fun showWriteTracks() {
        notificationManager.notify(
            NOTIFICATION_ID,
            builder.apply {
                setSmallIcon(R.drawable.ic_write)
                setContentTitle(context.getString(R.string.write_playlists))
                setProgress(0, 0, true)
                priority = NotificationCompat.PRIORITY_DEFAULT
                addAction(
                    R.drawable.ic_close_24px,
                    context.getString(R.string.cancel),
                    cancelPendingIntent
                )
            }.build()
        )
    }

    fun showExportComplete() {
        notificationManager.notify(
            NOTIFICATION_ID,
            builder.apply {
                setSmallIcon(R.drawable.ic_ok)
                setContentTitle(context.getString(R.string.export_complete))
                setProgress(0, 0, false)
                priority = NotificationCompat.PRIORITY_DEFAULT
                clearActions()
            }.build()
        )
    }

    fun showExportError(cause: String?) {
        notificationManager.notify(
            NOTIFICATION_ID,
            builder.apply {
                setSmallIcon(R.drawable.ic_error)
                setContentTitle(context.getString(R.string.export_error))
                setProgress(0, 0, false)
                setStyle(NotificationCompat.BigTextStyle().bigText(cause ?: EMPTY_STRING))
                priority = NotificationCompat.PRIORITY_DEFAULT
                clearActions()
            }.build()
        )
    }

    private fun createNotificationChannel() {
        //create the NotificationChannel, but only on API 26+ because
        //the NotificationChannel class is new and not in the support library
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
            //register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
    }

    private companion object {
        private const val CHANNEL_ID = "Playlists export"
        private const val NOTIFICATION_ID = 228996
        private const val ACTION_CANCEL = "action_cancel"
    }
}
