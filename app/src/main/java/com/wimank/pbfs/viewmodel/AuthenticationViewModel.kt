package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.R
import com.wimank.pbfs.domain.usecase.SessionManager
import com.wimank.pbfs.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthenticationViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    val authComplete = MutableLiveData(false)
    val authError = MutableLiveData<Event<Int>>()

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                sessionManager.flowSession().collect {
                    it.forEach { list ->
                        authComplete.postValue(list.accessToken.isNotEmpty() && (list.refreshToken.isNotEmpty()))
                    }
                }
            } catch (e: Exception) {
                authError.postValue(Event(R.string.authentication_error))
                Timber.e(e)
            }
        }
    }
}
