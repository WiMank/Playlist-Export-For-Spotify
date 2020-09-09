package com.wimank.pbfs.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.StringRes
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

//Export process animation for FAB
fun ExtendedFloatingActionButton.startExFabAnimation(context: Context) {
    if (isExtended)
        shrink(object : ExtendedFloatingActionButton.OnChangedCallback() {
            override fun onShrunken(extendedFab: ExtendedFloatingActionButton?) {
                startAnimation(
                    AnimationUtils.loadAnimation(
                        context,
                        R.anim.rotate
                    )
                )
            }
        })
}

//Clear animation and extend FAB
fun ExtendedFloatingActionButton.clearAnimationAndExtend() {
    clearAnimation()
    extend()
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
