package com.wimank.pbfs.ui.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.wimank.pbfs.ui.fragment.BackupFragmentDirections
import timber.log.Timber


class UiRouter(private val navController: NavController) {

    fun navigateToAuthenticationFragment() {
        navController.navigateSafe(BackupFragmentDirections.actionBackupFragmentToAuthenticationFragment())
    }

    private fun NavController.navigateSafe(navDirections: NavDirections? = null) {
        try {
            navDirections?.let {
                this.navigate(navDirections)
            }
        } catch (e: IllegalArgumentException) {
            Timber.e(e)
        }
    }

}
