plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.apptikar.feature.auth.presentation"
    compileSdk = 32

    defaultConfig {
        minSdk = 24
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose =  true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
}

dependencies {

    implementation(project(":feature:auth:domain"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))

    implementation(libs.androidx.core.kotlin)
    implementation (libs.androidx.compose.ui)
    implementation (libs.androidx.compose.material)
    implementation (libs.androidx.compose.tooling)
    implementation (libs.androidx.lifecycle.runtime)
    implementation (libs.androidx.lifecycle.compose)
    implementation (libs.androidx.activity.compose)
    implementation (libs.androidx.constraintlayout)
    implementation (libs.androidx.constraintlayout.compose)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.coroutines.play)




    implementation("com.google.android.gms:play-services-auth:20.4.0")
    implementation ("com.facebook.android:facebook-login:15.2.0")
    implementation("androidx.core:core-ktx:1.9.0")

    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso)
    androidTestImplementation (libs.androidx.compose.ui.test)
    debugImplementation (libs.androidx.compose.tooling)
    debugImplementation (libs.androidx.compose.ui.test.manifest)
    debugImplementation ("androidx.compose.ui:ui-tooling:1.4.0-alpha04")

    // navigationCompose
    implementation(libs.androidx.navigation.compose)

    //splash
    implementation(libs.androidx.core.splash)


    // hilt
    implementation (libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation)
}