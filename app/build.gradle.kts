import org.gradle.api.JavaVersion

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.spotifyclone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.spotifyclone"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildToolsVersion = "34.0.0"

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.android.car.ui:car-ui-lib:2.6.0")
    implementation("com.google.firebase:firebase-firestore:24.10.3")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("androidx.activity:activity:1.8.2")
    implementation("com.karumi:dexter:6.2.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Glide
//    implementation ("com.github.bumptech.glide:glide:4.11.0")
//    annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")
//    //multi layered sliding prompt
//    implementation("com.github.realgearinc:multi-sliding-up-panel:1.3.6")

}