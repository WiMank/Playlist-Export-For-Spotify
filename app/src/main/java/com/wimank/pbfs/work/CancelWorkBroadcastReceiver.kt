package com.wimank.pbfs.work

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.WorkManager
import com.wimank.pbfs.util.KEY_CANCEL
import com.wimank.pbfs.util.VALUE_CANCEL
import com.wimank.pbfs.util.WORK_TAG

/**
 * BroadcastReceiver cancels the task via a notification when the cancel key is pressed.
 */
class CancelWorkBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (it.getStringExtra(KEY_CANCEL) == VALUE_CANCEL) {
                if (context != null) {
                    WorkManager.getInstance(context).cancelUniqueWork(WORK_TAG)
                }
            }
        }
    }
}
