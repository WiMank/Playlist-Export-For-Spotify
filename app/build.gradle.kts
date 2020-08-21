import Dependencies.androidxJunit
import Dependencies.appcompat
import Dependencies.constraintlayout
import Dependencies.coreKtx
import Dependencies.coroutines
import Dependencies.daggerHilt
import Dependencies.espressoCore
import Dependencies.glide
import Dependencies.glideCompiler
import Dependencies.hiltAndroidCompiler
import Dependencies.junit
import Dependencies.kotlinStdlib
import Dependencies.lifecycleExtensions
import Dependencies.moshi
import Dependencies.moshiCodegen
import Dependencies.navigationComponent
import Dependencies.navigationComponentKtx
import Dependencies.retrofit
import Dependencies.viewModelKtx

plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    kotlin(Plugins.androidExtensions)
    kotlin(Plugins.kapt)
    id(Plugins.hiltPlugin)
    id("kotlin-android")
}

android {
    compileSdkVersion(App.compileSdkVersion)
    buildToolsVersion(App.buildToolsVersion)

    defaultConfig {
        applicationId = App.applicationId
        minSdkVersion(App.minSdkVersion)
        targetSdkVersion(App.targetSdkVersion)
        versionCode = App.versionCode
        versionName = App.versionName
        testInstrumentationRunner = App.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    kapt {
        correctErrorTypes = true
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlinStdlib)
    implementation(coreKtx)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    //Test
    testImplementation(junit)
    androidTestImplementation(androidxJunit)
    androidTestImplementation(espressoCore)

    //View
    implementation(appcompat)
    implementation(constraintlayout)

    //Dagger
    implementation(daggerHilt)
    kapt(hiltAndroidCompiler)

    //Navigation
    implementation(navigationComponent)
    implementation(navigationComponentKtx)

    //ViewModel
    implementation(viewModelKtx)
    implementation(lifecycleExtensions)

    //Coroutines
    implementation(coroutines)

    //Moshi
    implementation(moshi)
    kapt(moshiCodegen)

    //Retrofit
    implementation(retrofit)

    //Glide
    implementation(glide)
    kapt(glideCompiler)

}
