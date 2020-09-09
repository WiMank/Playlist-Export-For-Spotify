package com.wimank.pbfs.ui.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.wimank.pbfs.ui.fragment.AuthenticationFragmentDirections
import com.wimank.pbfs.ui.fragment.PlaylistFragmentDirections
import com.wimank.pbfs.ui.fragment.UserProfileDialogDirections
import timber.log.Timber


class UiRouter(private val navController: NavController) {

    fun navigateToPlaylistFragment() {
        navController.navigateSafe(
            AuthenticationFragmentDirections.actionAuthenticationFragmentToPlaylistFragment()
        )
    }

    fun navigateToUserProfileDialog() {
        navController.navigateSafe(
            PlaylistFragmentDirections.actionPlaylistFragmentToUserProfileDialog()
        )
    }

    fun navigateToAuthenticationFragment() {
        navController.navigateSafe(
            UserProfileDialogDirections.actionUserProfileDialogToAuthenticationFragment()
        )
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun getNavController() = navController

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
