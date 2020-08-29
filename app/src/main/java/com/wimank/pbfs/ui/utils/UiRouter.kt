package com.wimank.pbfs.ui.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import timber.log.Timber


class UiRouter(private val navController: NavController) {

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
