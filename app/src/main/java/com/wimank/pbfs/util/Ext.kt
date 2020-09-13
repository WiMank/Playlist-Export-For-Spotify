package com.wimank.pbfs.util

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.wimank.pbfs.R

//Required for spotify api access. Token + Bearer word
fun String.bearer(): String {
    return BEARER.plus(this)
}

//Disable or enable scrolling
fun RecyclerView.scroll(value: Boolean) {
    layoutManager = object : LinearLayoutManager(context) {
        override fun canScrollVertically(): Boolean = value
    }
}

//Shrink exfab and disable
fun ExtendedFloatingActionButton.startExFabLoadState() {
    if (isExtended)
        shrink(object : ExtendedFloatingActionButton.OnChangedCallback() {
            override fun onShrunken(extendedFab: ExtendedFloatingActionButton?) {
                isEnabled = false
            }
        })
}

//Extend exfab and enable
fun ExtendedFloatingActionButton.stopExFabLoadState() {
    if (!isExtended)
        extend(object : ExtendedFloatingActionButton.OnChangedCallback() {
            override fun onExtended(extendedFab: ExtendedFloatingActionButton?) {
                isEnabled = true
            }
        })
}

fun Activity.showSnackBar(view: View, @StringRes message: Int) {
    Snackbar.make(
        view,
        getString(message),
        Snackbar.LENGTH_SHORT
    ).show()
}

fun Activity.showSnackBarFileShare(view: View) {
    Snackbar.make(
        view,
        getString(R.string.export_complete),
        Snackbar.LENGTH_LONG
    ).setAction(getString(R.string.share_zip_file)) {
        ShareZipArchive(this).share()
    }.show()
}

fun Fragment.showSnackBar(view: View, @StringRes message: Int) {
    Snackbar.make(
        view,
        getString(message),
        Snackbar.LENGTH_SHORT
    ).show()
}

//Removing all buttons from a notification
@SuppressLint("RestrictedApi")
fun NotificationCompat.Builder.clearActions() {
    mActions.clear()
}
