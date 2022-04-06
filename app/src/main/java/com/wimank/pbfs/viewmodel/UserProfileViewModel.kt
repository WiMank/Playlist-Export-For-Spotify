package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.R
import com.wimank.pbfs.domain.model.User
import com.wimank.pbfs.domain.usecase.UserManager
import com.wimank.pbfs.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class UserProfileViewModel @ViewModelInject constructor(
    private val userManager: UserManager
) : ViewModel() {

    val data = MutableLiveData<User>()
    val update = MutableLiveData(false)
    val event = MutableLiveData<Event<EventMessage>>()

    init {
        startLoad()
    }

    /**
     * Loading user data.
     */
    private fun startLoad() {
        viewModelScope.launch(context = Dispatchers.IO) {
            update.progressWrapper {
                runCatching {
                    userManager.loadUser()
                }.onSuccess { userFlow ->
                    collectUserFlow(userFlow)
                }.onFailure { ex ->
                    event.postValue(Event(LoadError(R.string.user_data_load_failed)))
                    Timber.e(ex)
                }
            }
        }
    }

    /**
     * Deleting user data.
     */
    fun logout() {
        viewModelScope.launch(context = Dispatchers.IO) {
            userManager.logout()
            event.postValue(Event(Logout(R.string.sign_out)))
        }
    }

    private suspend fun collectUserFlow(userFlow: Flow<User>) {
        userFlow.collect {
            data.postValue(it)
            update.postValue(false)
        }
    }
}
