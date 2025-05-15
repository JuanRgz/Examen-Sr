plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.development.cursoandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.development.cursoandroid"
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
    buildFeatures{
        viewBinding = true
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

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.fragment.ktx)

    implementation(libs.retrofit)
    implementation(libs.gson.converter)
    implementation(libs.gson)

    //LiveData
    implementation(libs.viewmodel)
    implementation(libs.livedata)

    //Concat Adapter
//    implementation(libs.recyclerview)
    implementation(libs.androidx.recyclerview)

    //Glide
    implementation(libs.glide)

    //Room
    /*implementation(libs.bundles.room)
    ksp(libs.room.compiler)*/
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    implementation(libs.firebase.firestore)
    ksp(libs.androidx.room.compiler)

    implementation(libs.core.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}