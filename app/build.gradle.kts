plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.androidx.navigation.safeargs)
}

android {
    namespace = "edu.unaigomdie.superhero2024"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.unaigomdie.superhero2024"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //librerias para la app
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation (libs.retrofit)
    implementation(libs.gson.serializer)
    implementation(libs.converter.gson)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //implementation(libs.logging.interceptor)
    implementation(libs.glide)
    implementation(libs.androidx.cardview)
    implementation(libs.coil)
    implementation (libs.com.faltenreich.skeletonlayout)
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)
    implementation(libs.nav.fragment.ktx)
    implementation(libs.androidx.navigation.safe.args.gradle.plugin)



    //librerias para el testing
    testImplementation(libs.junit)
    //librerias android testing
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}