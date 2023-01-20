plugins {
    id("com.android.library")
    id ("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.apptikar.feature.chat.presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

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

    implementation(project(":core:designsystem"))
    implementation(project(":feature:chat:domain"))
    implementation(project(":feature:chat:data"))

    implementation(libs.androidx.core.kotlin)
    implementation (libs.androidx.compose.ui)
    implementation (libs.androidx.compose.material)
    implementation (libs.androidx.compose.tooling)
    implementation (libs.androidx.lifecycle.runtime)
    implementation (libs.androidx.lifecycle.compose)
    implementation (libs.androidx.activity.compose)
    implementation (libs.androidx.constraintlayout)
    implementation (libs.androidx.constraintlayout.compose)
    implementation(project(mapOf("path" to ":core:common")))
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
    kapt (libs.hilt.compiler)
    kapt (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation)





    implementation("io.coil-kt:coil-compose:2.2.2")
}