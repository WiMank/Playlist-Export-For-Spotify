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
    id(Plugins.junit5Plugin)
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

    //Test
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.junitJupiterApi)
    testRuntimeOnly(Dependencies.junitJupiterEngine)
    testImplementation(Dependencies.junitJupiterParams)
    androidTestImplementation(Dependencies.androidxJunit)
    androidTestImplementation(Dependencies.espressoCore)
    testImplementation(Dependencies.mockk)
    androidTestImplementation(Dependencies.mockkAndroid)
    androidTestImplementation(Dependencies.daggerHiltTesting)
    kaptAndroidTest(Dependencies.daggerHiltAndroidCompiler)
    testImplementation(Dependencies.coroutinesTest)
    androidTestImplementation(Dependencies.truth)
    androidTestImplementation(Dependencies.workTesting)
    debugImplementation(Dependencies.fragmentTesting)

    //Activity, Fragment, etc.
    implementation(Dependencies.appcompat)
    implementation(Dependencies.constraintlayout)
    implementation(Dependencies.activityKtx)
    implementation (Dependencies.fragmentKtx)

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
