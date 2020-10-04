package com.wimank.pbfs.utils

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class WorkManageRule : TestRule {

    override fun apply(base: Statement?, description: Description?) = WorkManagerStatement(base)

    inner class WorkManagerStatement(private val base: Statement?) : Statement() {

        override fun evaluate() {

            fun initWorkManager() {
                val context = InstrumentationRegistry.getInstrumentation().targetContext
                val config = Configuration.Builder()
                    // Set log level to Log.DEBUG to make it easier to debug
                    .setMinimumLoggingLevel(Log.DEBUG)
                    // Use a SynchronousExecutor here to make it easier to write tests
                    .setExecutor(SynchronousExecutor())
                    .build()

                // Initialize WorkManager for instrumentation tests.
                WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
            }
            initWorkManager()
            base?.evaluate()
        }
    }
}
