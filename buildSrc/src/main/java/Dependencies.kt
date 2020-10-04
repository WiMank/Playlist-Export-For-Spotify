import Version.androidJunitVersion
import Version.appAuthVersion
import Version.appcompatVersion
import Version.constraintlayoutVersion
import Version.coroutinesVersion
import Version.daggerHiltLifecycleVersion
import Version.daggerHiltVersion
import Version.daggerHiltWorkVersion
import Version.espressoVersion
import Version.glideVersion
import Version.junitJupiterVersion
import Version.junitVersion
import Version.kotlinGradlePluginVersion
import Version.kotlinVersion
import Version.kotlinxHtmlVersion
import Version.ktxVersion
import Version.liveDataKtxVersion
import Version.loggingInterceptorVersion
import Version.materialVersion
import Version.mockkVersion
import Version.moshiVersion
import Version.navigationComponentVersion
import Version.navigationSafeArgsVersion
import Version.okHttpVersion
import Version.pluginsAndroidJunit5Version
import Version.retrofitVersion
import Version.roomVersion
import Version.shimmerVersion
import Version.timberVersion
import Version.toolsGradleVersion
import Version.truthVersion
import Version.viewModelVersion
import Version.workManagerVersion
import Version.workTestingVersion
import Version.ztZipVersion

object Dependencies {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

    const val coreKtx = "androidx.core:core-ktx:$ktxVersion"

    const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"

    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:$constraintlayoutVersion"

    const val junit = "junit:junit:$junitVersion"

    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion"
    const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion"
    const val junitJupiterParams = "org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion"
    const val pluginsAndroidJunit5 =
        "de.mannodermaus.gradle.plugins:android-junit5:$pluginsAndroidJunit5Version"
    const val androidxJunit = "androidx.test.ext:junit:$androidJunitVersion"

    const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"

    const val toolsBuildGradle = "com.android.tools.build:gradle:$toolsGradleVersion"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinGradlePluginVersion"

    const val daggerHilt = "com.google.dagger:hilt-android:$daggerHiltVersion"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$daggerHiltVersion"
    const val hiltClassPathPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:$daggerHiltVersion"
    const val daggerHiltLifecycle =
        "androidx.hilt:hilt-lifecycle-viewmodel:$daggerHiltLifecycleVersion"
    const val daggerHiltWork = "androidx.hilt:hilt-work:$daggerHiltWorkVersion"
    const val daggerHiltCompiler = "androidx.hilt:hilt-compiler:$daggerHiltLifecycleVersion"
    const val daggerHiltTesting = "com.google.dagger:hilt-android-testing:$daggerHiltVersion"
    const val daggerHiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:$daggerHiltVersion"

    const val navigationComponent =
        "androidx.navigation:navigation-fragment-ktx:$navigationComponentVersion"
    const val navigationComponentKtx =
        "androidx.navigation:navigation-ui-ktx:$navigationComponentVersion"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationSafeArgsVersion"

    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${viewModelVersion}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${viewModelVersion}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"

    const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion"

    const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"

    const val timber = "com.jakewharton.timber:timber:$timberVersion"

    const val room = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"

    const val appAuth = "net.openid:appauth:$appAuthVersion"

    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$liveDataKtxVersion"

    const val shimmer = "com.facebook.shimmer:shimmer:${shimmerVersion}"

    const val workManager = "androidx.work:work-runtime-ktx:$workManagerVersion"

    const val kotlinxHtml = "org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion"

    const val material = "com.google.android.material:material:$materialVersion"

    const val ztZip = "org.zeroturnaround:zt-zip:$ztZipVersion"

    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"

    const val truth = "com.google.truth:truth:$truthVersion"

    const val workTesting = "androidx.work:work-testing:$workTestingVersion"
}

object Version {
    const val kotlinVersion = "1.4.0"
    const val ktxVersion = "1.3.1"
    const val appcompatVersion = "1.2.0"
    const val constraintlayoutVersion = "1.1.3"
    const val junitVersion = "4.13"
    const val androidJunitVersion = "1.1.1"
    const val junitJupiterVersion = "5.6.2"
    const val espressoVersion = "3.2.0"
    const val toolsGradleVersion = "4.0.1"
    const val kotlinGradlePluginVersion = kotlinVersion
    const val daggerHiltVersion = "2.28.3-alpha"
    const val daggerHiltLifecycleVersion = "1.0.0-alpha02"
    const val daggerHiltWorkVersion = "1.0.0-alpha02"
    const val navigationComponentVersion = "2.3.0"
    const val viewModelVersion = "2.1.0-beta01"
    const val coroutinesVersion = "1.3.9"
    const val moshiVersion = "1.9.3"
    const val retrofitVersion = "2.9.0"
    const val glideVersion = "4.11.0"
    const val timberVersion = "4.7.1"
    const val navigationSafeArgsVersion = "2.3.0"
    const val roomVersion = "2.2.5"
    const val okHttpVersion = "4.8.1"
    const val loggingInterceptorVersion = "4.8.1"
    const val appAuthVersion = "0.7.1"
    const val liveDataKtxVersion = "2.2.0"
    const val shimmerVersion = "0.5.0"
    const val workManagerVersion = "2.4.0"
    const val kotlinxHtmlVersion = "0.7.2"
    const val materialVersion = "1.2.1"
    const val ztZipVersion = "1.14"
    const val mockkVersion = "1.10.0"
    const val pluginsAndroidJunit5Version = "1.6.2.0"
    const val truthVersion = "1.0.1"
    const val workTestingVersion = "2.4.0"
}
