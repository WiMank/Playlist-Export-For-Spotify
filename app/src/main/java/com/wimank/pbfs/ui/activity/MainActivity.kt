package com.wimank.pbfs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.wimank.pbfs.R
import com.wimank.pbfs.ui.utils.UiRouter


class MainActivity : AppCompatActivity() {

    private companion object;

    private val uiRouter: UiRouter by lazy {
        UiRouter(findNavController(R.id.main_nav_host))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}
