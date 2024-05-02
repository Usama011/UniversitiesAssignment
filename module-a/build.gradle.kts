plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("kotlin-parcelize")
}

android {
    namespace = "com.xische.assigment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.xische.assigment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.xische.assigment.data.universities.CustomRunner"

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
    dataBinding {
       this.enable = true
    }
    buildFeatures {
        this.viewBinding = true
    }

}

dependencies {

    implementation(project(":module-b"))
    //Core lib
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    //Test lib
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.core.testing)
    testImplementation(libs.google.truth)
    testImplementation(libs.hilt.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation (libs.androidx.runner)

    androidTestImplementation(libs.mockito)
    androidTestImplementation(libs.mockito.android)
    androidTestImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.core.testing)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.hilt.testing)
    androidTestImplementation (libs.androidx.runner)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Hilt lib
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.androidx.compiler)
    //Retrofit lib
    implementation(libs.retrofit)
    implementation(libs.retrofit.convertor)
    implementation(libs.retrofit.logging)
    //Coroutines lib
    implementation(libs.jetbrains.coroutines)
    implementation(libs.jetbrains.coroutines.android)
    //ViewModel lib
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.viewmodel)
    implementation(libs.androidx.fragment.ktx)
    //Room lib
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)


}