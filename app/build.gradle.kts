plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.assignment3_hebaalsayyed_301357388"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.assignment3_hebaalsayyed_301357388"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        val googleMapsApiKey: String? = project.properties["GoogleMapsAPIKey"]?.toString()
        if (googleMapsApiKey != null) {
            buildConfigField("String", "GOOGLE_MAPS_API_KEY", "\"$googleMapsApiKey\"")
        }
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.libraries.places:places:3.3.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")


    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("androidx.room:room-runtime:2.6.0")
    // room ktx
    implementation ("androidx.room:room-ktx:2.6.0")
    annotationProcessor ("androidx.room:room-compiler:2.6.0")
    // For Kotlin use kapt instead of annotationProcessor
    kapt("androidx.room:room-compiler:2.6.0")

}