import Version.androidJunitVersion
import Version.appcompatVersion
import Version.constraintlayoutVersion
import Version.coroutinesVersion
import Version.daggerHiltVersion
import Version.espresso
import Version.glideVersion
import Version.junitVersion
import Version.kotlin
import Version.kotlinGradlePluginVersion
import Version.ktx
import Version.moshiVersion
import Version.navigationComponentVersion
import Version.retrofitVersion
import Version.toolsGradle
import Version.viewModel


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

    const val daggerHilt = "com.google.dagger:hilt-android:$daggerHiltVersion"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$daggerHiltVersion"
    const val hiltClassPathPlugin = "com.google.dagger:hilt-android-gradle-plugin:$daggerHiltVersion"

    const val navigationComponent = "androidx.navigation:navigation-fragment-ktx:$navigationComponentVersion"
    const val navigationComponentKtx = "androidx.navigation:navigation-ui-ktx:$navigationComponentVersion"

    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${viewModel}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${viewModel}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"

    const val moshi = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    const val retrofit ="com.squareup.retrofit2:retrofit:$retrofitVersion"

    const val glide ="com.github.bumptech.glide:glide:$glideVersion"
    const val glideCompiler ="com.github.bumptech.glide:compiler:$glideVersion"
}

object Version {
    const val kotlin = "1.4.0"
    const val ktx = "1.3.1"
    const val appcompatVersion = "1.2.0"
    const val constraintlayoutVersion = "1.1.3"
    const val junitVersion = "4.13"
    const val androidJunitVersion = "1.1.1"
    const val espresso = "3.2.0"
    const val toolsGradle = "4.0.1"
    const val kotlinGradlePluginVersion = kotlin
    const val daggerHiltVersion = "2.28.3-alpha"
    const val navigationComponentVersion = "2.3.0"
    const val viewModel = "2.1.0-beta01"
    const val coroutinesVersion = "1.3.9"
    const val moshiVersion = "1.9.3"
    const val retrofitVersion = "2.9.0"
    const val glideVersion = "4.11.0"
}
