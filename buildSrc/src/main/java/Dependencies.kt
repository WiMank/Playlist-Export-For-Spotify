import Version.androidJunitVersion
import Version.appcompatVersion
import Version.constraintlayoutVersion
import Version.espresso
import Version.junitVersion
import Version.kotlin
import Version.kotlinGradlePluginVersion
import Version.ktx
import Version.toolsGradle

object Dependencies {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin"
    const val coreKtx = "androidx.core:core-ktx:$ktx"
    const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:$constraintlayoutVersion"
    const val junit = "junit:junit:$junitVersion"
    const val androidxJunit = "androidx.test.ext:junit:$androidJunitVersion"
    const val espressoCore = "androidx.test.espresso:espresso-core:$espresso"
    const val toolsBuildGradle = "com.android.tools.build:gradle:$toolsGradle"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinGradlePluginVersion"

}

object Version {
    const val kotlin = "1.3.72"
    const val ktx = "1.3.1"
    const val appcompatVersion = "1.2.0"
    const val constraintlayoutVersion = "1.1.3"
    const val junitVersion = "4.13"
    const val androidJunitVersion = "1.1.1"
    const val espresso = "3.2.0"
    const val toolsGradle = "4.0.1"
    const val kotlinGradlePluginVersion = kotlin

}
