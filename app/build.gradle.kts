plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlinx-serialization")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")

}

android {
    namespace = "com.apptikar.chatbox"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.apptikar.chatbox"
        minSdk = 24
        targetSdk = 33
        versionCode =  1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(project(":core:common"))
    implementation(project(":feature:auth:presentation"))
    implementation(project(":feature:auth:data"))
    implementation(project(":feature:auth:domain"))
    implementation(project(":feature:chat:presentation"))
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
    implementation ("androidx.activity:activity-ktx:1.6.1")                     //todo
    implementation ("com.facebook.android:facebook-android-sdk:15.2.0")
    implementation(project(mapOf("path" to ":feature:chat:data")))
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.2")
    implementation("com.google.android.gms:play-services-auth:20.4.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

//        implementation ("com.facebook.android:facebook-core:5.15.3")
//    implementation ("com.facebook.android:facebook-common:5.15.3")


    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso)
    androidTestImplementation (libs.androidx.compose.ui.test)
    debugImplementation (libs.androidx.compose.tooling)
    debugImplementation (libs.androidx.compose.ui.test.manifest)






    //sdp and ssp dependency
    implementation (libs.ssp)
    implementation (libs.sdp)

    // navigationCompose
    implementation(libs.androidx.navigation.compose)



    // lifecycle
    implementation (libs.androidx.lifecycle.common)
    implementation (libs.androidx.lifecycle.extensions)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.lifecycle.runtime)


    // windowManger
    implementation(libs.window.manager)

    // hilt
    implementation (libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation)


    //retrofit
    implementation (libs.bundles.retrofit.bundle)


    //splash
    implementation(libs.androidx.core.splash)

    //dataStore
    implementation (libs.androidx.datastore.pref)
    implementation (libs.androidx.datastore.core)

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
    implementation("io.ktor:ktor-client-auth:$ktor_version")


}