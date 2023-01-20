plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlinx-serialization")
    id ("kotlin-kapt")
}

android {
    namespace = "com.apptikar.feature.auth.data"
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
}

dependencies {


    implementation(project(":feature:auth:domain"))
    implementation(project(":core:common"))

    implementation(libs.androidx.core.kotlin)
    implementation (libs.androidx.lifecycle.runtime)

    // hilt
    implementation (libs.hilt.android)
    implementation("androidx.core:core-ktx:+")
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation)

    //dataStore
    implementation (libs.androidx.datastore.pref)
    implementation (libs.androidx.datastore.core)

    //retrofit
    implementation (libs.bundles.retrofit.bundle)

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    //ktor client
    val ktor_version = "2.2.2"

    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-client-websockets:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
}