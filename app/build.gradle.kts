plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.augurit.kundroid"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions{
        kotlinCompilerExtensionVersion= libs.versions.compose.get()
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.swiperefresh)
    implementation(libs.bundles.compose)
    implementation(libs.hilt.android.runtime)
    kapt( libs.hilt.android.compiler)
    implementation(libs.bundles.net.request)
    implementation(libs.coil)
    implementation(libs.bundles.room)
    implementation(libs.room.paging)
    kapt(libs.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.test)
    debugImplementation(libs.compose.ui.tooling)
}