plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    kotlin(Plugins.androidExtensions)
    kotlin(Plugins.kapt)
    id(Plugins.hiltPlugin)
    id(Plugins.safeargsKotlinPlugin)
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
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.coreKtx)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    //Test
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.junitJupiter)
    androidTestImplementation(Dependencies.androidxJunit)
    androidTestImplementation(Dependencies.espressoCore)

    //View
    implementation(Dependencies.appcompat)
    implementation(Dependencies.constraintlayout)

    //Room
    implementation(Dependencies.room)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)

    //Dagger
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.hiltAndroidCompiler)

    //Dagger Lifecycle
    implementation(Dependencies.daggerHiltLifecycle)
    kapt(Dependencies.daggerHiltCompiler)

    //Dagger Work Manager
    implementation(Dependencies.daggerHiltWork)

    //Navigation
    implementation(Dependencies.navigationComponent)
    implementation(Dependencies.navigationComponentKtx)

    //ViewModel
    implementation(Dependencies.viewModelKtx)
    implementation(Dependencies.lifecycleExtensions)
    implementation(Dependencies.liveDataKtx)

    //Coroutines
    implementation(Dependencies.coroutines)

    //Moshi
    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiCodegen)

    //Retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverterMoshi)

    //OkHttp
    implementation(Dependencies.okHttp)
    implementation(Dependencies.loggingInterceptor)

    //Glide
    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)

    //Timber
    implementation(Dependencies.timber)

    //SpotifyAuth
    implementation(Dependencies.spotifyAuth)

    //AppAuth
    implementation(Dependencies.appAuth)

    //Shimmer
    implementation(Dependencies.shimmer)

    //Work Manager
    implementation(Dependencies.workManager)

    //KotlinxHtml
    implementation(Dependencies.kotlinxHtml)

    //Material components
    implementation(Dependencies.material)

    //ZtZip
    implementation(Dependencies.ztZip)

}
