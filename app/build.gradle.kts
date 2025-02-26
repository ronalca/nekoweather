plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)

    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.ralcala.nekoweather"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ralcala.nekoweather"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0-alpha"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables { useSupportLibrary = true }

        buildConfigField("String", "WEATHER_BASE_URL", "\"https://api.openweathermap.org/\"")
        buildConfigField("String", "WEATHER_API_KEY", "\"your_api_key_here\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.material)
    implementation(libs.material.icons)
    implementation(libs.activity.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // navigation compose and serialization
    implementation(libs.navigation.compose)
    implementation(libs.serialization.json)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

//    implementation(libs.splash.screen)//splash screen library
//
//    implementation(libs.datastore.preferences)
//
//    implementation(libs.threetenabp)

    implementation(libs.coil.compose)

    // Hilt - Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

//    // ViewModel & LiveData
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    // geolocation
    implementation("com.google.android.gms:play-services-location:21.3.0")
}