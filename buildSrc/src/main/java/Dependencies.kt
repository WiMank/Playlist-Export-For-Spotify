import Version.androidJunitVersion
import Version.appcompatVersion
import Version.constraintlayoutVersion
import Version.coroutinesVersion
import Version.daggerHiltVersion
import Version.espressoVersion
import Version.glideVersion
import Version.junitVersion
import Version.kotlinGradlePluginVersion
import Version.kotlinVersion
import Version.ktxVersion
import Version.loggingInterceptorVersion
import Version.moshiVersion
import Version.navigationComponentVersion
import Version.navigationSafeArgsVersion
import Version.okHttpVersion
import Version.retrofitVersion
import Version.roomVersion
import Version.spotifyAuthVersion
import Version.timberVersion
import Version.toolsGradleVersion
import Version.viewModelVersion


object Dependencies {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

    const val coreKtx = "androidx.core:core-ktx:$ktxVersion"

    const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"

    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:$constraintlayoutVersion"

    const val junit = "junit:junit:$junitVersion"

    const val androidxJunit = "androidx.test.ext:junit:$androidJunitVersion"

    const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"

    const val toolsBuildGradle = "com.android.tools.build:gradle:$toolsGradleVersion"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinGradlePluginVersion"

    const val daggerHilt = "com.google.dagger:hilt-android:$daggerHiltVersion"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$daggerHiltVersion"
    const val hiltClassPathPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:$daggerHiltVersion"

    const val navigationComponent =
        "androidx.navigation:navigation-fragment-ktx:$navigationComponentVersion"
    const val navigationComponentKtx =
        "androidx.navigation:navigation-ui-ktx:$navigationComponentVersion"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationSafeArgsVersion"

    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${viewModelVersion}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${viewModelVersion}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"

    const val moshi = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion"

    const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"

    const val timber = "com.jakewharton.timber:timber:$timberVersion"

    const val spotifyAuth = "com.spotify.android:auth:$spotifyAuthVersion"

    const val room = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
}

object Version {
    const val kotlinVersion = "1.4.0"
    const val ktxVersion = "1.3.1"
    const val appcompatVersion = "1.2.0"
    const val constraintlayoutVersion = "1.1.3"
    const val junitVersion = "4.13"
    const val androidJunitVersion = "1.1.1"
    const val espressoVersion = "3.2.0"
    const val toolsGradleVersion = "4.0.1"
    const val kotlinGradlePluginVersion = kotlinVersion
    const val daggerHiltVersion = "2.28.3-alpha"
    const val navigationComponentVersion = "2.3.0"
    const val viewModelVersion = "2.1.0-beta01"
    const val coroutinesVersion = "1.3.9"
    const val moshiVersion = "1.9.3"
    const val retrofitVersion = "2.9.0"
    const val glideVersion = "4.11.0"
    const val timberVersion = "4.7.1"
    const val navigationSafeArgsVersion = "2.3.0"
    const val spotifyAuthVersion = "1.2.3"
    const val roomVersion = "2.2.5"
    const val okHttpVersion = "4.8.1"
    const val loggingInterceptorVersion = "4.8.1"
}
