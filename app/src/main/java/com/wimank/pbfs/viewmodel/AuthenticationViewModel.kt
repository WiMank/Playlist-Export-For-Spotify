package com.wimank.pbfs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthenticationViewModel : ViewModel() {

    val authComplete = MutableLiveData(false)

    fun showCompleteActionForAuth() {
        authComplete.value = true
    }
}
