package com.wimank.pbfs.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.wimank.pbfs.R


class MainActivity : AppCompatActivity() {

    private val uiRouter: UiRouter by lazy {
        UiRouter(findNavController(R.id.main_nav_host))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
