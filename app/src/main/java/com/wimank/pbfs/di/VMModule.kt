package com.wimank.pbfs.di

import androidx.lifecycle.ViewModel
import com.wimank.pbfs.viewmodel.MainActivityViewModel
import com.wimank.pbfs.viewmodel.PlaylistViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ApplicationComponent::class)
@Suppress("unused")
interface VMModule {

    @IntoMap
    @Binds
    @ViewModelKey(PlaylistViewModel::class)
    fun provideBackupViewModel(vm: PlaylistViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    fun provideMainActivityViewModel(vm: MainActivityViewModel): ViewModel

}
