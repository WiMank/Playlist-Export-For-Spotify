package com.wimank.pbfs.ui.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.UserLayoutBinding
import com.wimank.pbfs.util.LoadError
import com.wimank.pbfs.util.Logout
import com.wimank.pbfs.util.URL_REPOSITORY
import com.wimank.pbfs.util.showSnackBar
import com.wimank.pbfs.viewmodel.UserProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileDialog : BottomSheetDialogFragment() {

    private lateinit var dataBinding: UserLayoutBinding
    private val viewModel: UserProfileViewModel by viewModels()
    private var userProfileDialogCallback: UserProfileDialogCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.user_layout, container, false)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel

        dataBinding.close.setOnClickListener {
            dismiss()
        }

        dataBinding.github.setOnClickListener {
            goToGithub()
        }

        //observe events
        viewModel.event.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                if (it is LoadError) {
                    showSnackBar(dataBinding.userCl, it.message)
                }

                if (it is Logout) {
                    showSnackBar(dataBinding.userCl, it.message)
                    userProfileDialogCallback?.logout()
                }
            }
        }
        return dataBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UserProfileDialogCallback)
            userProfileDialogCallback = context
    }

    /**
     * Go to the repository of this app.
     */
    private fun goToGithub() {
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(URL_REPOSITORY)
            startActivity(this)
        }
    }

    /**
     * [UserProfileDialog] callback.
     */
    interface UserProfileDialogCallback {
        /**
         * Called when the logout button is clicked.
         */
        fun logout()
    }
}
