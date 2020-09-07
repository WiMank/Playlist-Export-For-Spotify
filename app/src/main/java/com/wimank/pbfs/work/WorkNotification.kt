package com.wimank.pbfs.work

import android.content.Context
import androidx.core.app.NotificationCompat
import com.wimank.pbfs.util.CHANNEL_ID
import dagger.hilt.android.qualifiers.ApplicationContext

class WorkNotification(@ApplicationContext private val context: Context) {

    var builder = NotificationCompat.Builder(context, CHANNEL_ID)


}
