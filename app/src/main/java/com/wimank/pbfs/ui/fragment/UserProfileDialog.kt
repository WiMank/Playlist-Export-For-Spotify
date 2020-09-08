package com.wimank.pbfs.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wimank.pbfs.databinding.UserLayoutBinding

class UserProfileDialog : BottomSheetDialogFragment() {

    private lateinit var dataBinding: UserLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = UserLayoutBinding.inflate(layoutInflater)


        return dataBinding.root
    }
}
