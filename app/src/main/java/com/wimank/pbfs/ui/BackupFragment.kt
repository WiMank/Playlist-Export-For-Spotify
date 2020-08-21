package com.wimank.pbfs.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.BackupFragmentBinding
import com.wimank.pbfs.viewmodel.BackupViewModel


class BackupFragment : BaseFragment<BackupFragmentBinding>() {

    private val viewModel: BackupViewModel by viewModels()

    override fun getLayoutRes() = R.layout.backup_fragment

    override fun iniView(savedInstanceState: Bundle?) {

    }

    override fun setBindingViewModel() {

    }

}
