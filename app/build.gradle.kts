import  com.android.build.gradle.internal.dsl.BuildType
import java.io.FileInputStream
import java.util.*

plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    kotlin(Plugins.androidExtensions)
    kotlin(Plugins.kapt)
    id(Plugins.hiltPlugin)
    id(Plugins.safeargsKotlinPlugin)
}

val secretsPropertiesFile = rootProject.file("secrets.properties")
val secretProperties = Properties()

if (secretsPropertiesFile.exists()) {
    secretProperties.load(FileInputStream(secretsPropertiesFile))
} else {
    secretProperties.setProperty("client_id", System.getenv("client_id"))
    secretProperties.setProperty("redirect_uri", System.getenv("redirect_uri"))
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
            buildClientId()
            buildRedirectUri()
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        getByName("debug") {
            buildClientId()
            buildRedirectUri()
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


    testImplementation("io.mockk:mockk:1.10.0")
    androidTestImplementation("io.mockk:mockk-android:1.10.0")

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.28.3-alpha")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.28.3-alpha")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.9")


}

fun BuildType.buildClientId() {
    buildConfigField(
        "String",
        "clientId",
        "\"${secretProperties.getProperty("client_id")}\""
    )
}

fun BuildType.buildRedirectUri() {
    buildConfigField(
        "String",
        "redirectUri",
        "\"${secretProperties.getProperty("redirect_uri")}\""
    )
}
