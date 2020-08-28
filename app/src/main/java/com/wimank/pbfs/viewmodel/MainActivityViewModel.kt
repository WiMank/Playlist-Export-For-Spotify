package com.wimank.pbfs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.domain.usecase.SessionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(private val sessionUseCase: SessionUseCase) :
    ViewModel() {

    fun checkAuthorize() {
        viewModelScope.launch(context = Dispatchers.IO) {
            Timber.i("AIFDNJIAN ${sessionUseCase.getRepository().getSession()}")
            sessionUseCase.getRepository().getSession()
        }
    }

}
