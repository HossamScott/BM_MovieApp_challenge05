plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.hossam.bm_movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hossam.bm_movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
    buildFeatures {
        compose = true // Enable Jetpack Compose
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation ("io.mockk:mockk:1.13.5")

    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.inline)
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    testImplementation ("io.mockk:mockk:1.13.5")  // or latest version
    testImplementation ("net.bytebuddy:byte-buddy:1.12.0")  // or latest version


    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.core:core-ktx:1.9.0")
    // Compose
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.compose.ui:ui-tooling:1.5.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.activity:activity-compose:1.8.0")

// Navigation
    implementation("androidx.navigation:navigation-compose:2.7.3")

// ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

// Retrofit and Moshi for network calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

// Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// Room for caching
    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")

// Dependency Injection
    implementation("io.insert-koin:koin-androidx-compose:3.4.5")

// for image loading
    implementation("com.github.skydoves:landscapist-glide:1.4.0")

// Logging
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

}