package com.wimank.pbfs.util

import android.content.Context
import androidx.core.content.edit
import com.wimank.pbfs.EMPTY_STRING
import com.wimank.pbfs.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    private companion object {
        //Key preferences for token
        const val PREF_TOKEN_KEY = "access_token"
    }

    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )


    fun putToken(token: String) {
        sharedPref.edit { putString(PREF_TOKEN_KEY, token) }
    }

    fun getToken(): String {
        return sharedPref.getString(PREF_TOKEN_KEY, EMPTY_STRING) ?: EMPTY_STRING
    }

}
