package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.R
import com.wimank.pbfs.domain.model.User
import com.wimank.pbfs.domain.usecase.UserManager
import com.wimank.pbfs.util.Event
import com.wimank.pbfs.util.EventMessage
import com.wimank.pbfs.util.LoadError
import com.wimank.pbfs.util.Logout
import kotlinx.coroutines.Dispatchers
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

    private fun startLoad() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                update.postValue(true)
                userManager.loadUser().collect {
                    data.postValue(it)
                    update.postValue(false)
                }

            } catch (e: Exception) {
                Timber.e(e)
                event.postValue(Event(LoadError(R.string.user_data_load_failed)))
            } finally {
                update.postValue(false)
            }
        }
    }

    fun logout() {
        viewModelScope.launch(context = Dispatchers.IO) {
            userManager.logout()
            event.postValue(Event(Logout(R.string.sign_out)))
        }
    }
}
