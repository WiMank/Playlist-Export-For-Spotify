import Dependencies.androidxJunit
import Dependencies.appcompat
import Dependencies.constraintlayout
import Dependencies.coreKtx
import Dependencies.espressoCore
import Dependencies.kotlinStdlib
import Dependencies.junit

plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    kotlin(Plugins.androidExtensions)
    kotlin(Plugins.kapt)
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlinStdlib)
    implementation(coreKtx)
    implementation(appcompat)
    implementation(constraintlayout)
    testImplementation(junit)
    androidTestImplementation(androidxJunit)
    androidTestImplementation(espressoCore)

}
