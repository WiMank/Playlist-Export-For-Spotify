package com.wimank.pbfs.fragment

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wimank.pbfs.utils.WorkManageRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class AuthenticationFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var workManageRule = WorkManageRule()

    @Test
    fun verifyInjection() {

    }
}