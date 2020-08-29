package com.wimank.pbfs.ui.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.wimank.pbfs.ui.fragment.AuthenticationFragmentDirections
import timber.log.Timber


class UiRouter(private val navController: NavController) {

    fun navigateToPlaylistFragment() {
        navController.navigateSafe(
            AuthenticationFragmentDirections.actionAuthenticationFragmentToPlaylistFragment()
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
