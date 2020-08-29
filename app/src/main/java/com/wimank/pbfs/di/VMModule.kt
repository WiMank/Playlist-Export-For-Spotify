package com.wimank.pbfs.di

import androidx.lifecycle.ViewModel
import com.wimank.pbfs.viewmodel.MainActivityViewModel
import com.wimank.pbfs.viewmodel.PlaylistViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
@Suppress("unused")
interface FragmentVMModule {

    @IntoMap
    @Binds
    @ViewModelKey(PlaylistViewModel::class)
    fun provideBackupViewModel(vm: PlaylistViewModel): ViewModel

}

@Module
@InstallIn(ActivityComponent::class)
@Suppress("unused")
interface ActivityVMModule {

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    fun provideMainActivityViewModel(vm: MainActivityViewModel): ViewModel

}
