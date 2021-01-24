// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.toolsBuildGradle)
        classpath(Dependencies.kotlinGradlePlugin)
        classpath(Dependencies.hiltClassPathPlugin)
        classpath(Dependencies.navigationSafeArgs)
        classpath(Dependencies.pluginsAndroidJunit5)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
