package com.wimank.pbfs.di

import androidx.lifecycle.ViewModel
import com.wimank.pbfs.viewmodel.PlaylistViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
@Suppress("unused")
interface VMModule {

    @IntoMap
    @Binds
    @ViewModelKey(PlaylistViewModel::class)
    @FragmentScoped
    fun provideBackupViewModel(vm: PlaylistViewModel): ViewModel

}
