package com.wimank.pbfs.activity

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.ui.activity.MainActivity
import com.wimank.pbfs.utils.WorkManageRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var workManageRule = WorkManageRule()

    @Test
    fun verifyInjection() {
        ActivityScenario.launch(MainActivity::class.java).use {
            it.moveToState(Lifecycle.State.RESUMED)
            it.onActivity { activity ->
                assertThat(activity.viewModel).isNotNull()
                activity.connectivityWatcher.observe(activity) { value ->
                    assertThat(value).isNotNull()
                }
            }
        }
    }
}
