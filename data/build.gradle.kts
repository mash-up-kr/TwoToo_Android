@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias (libs.plugins.android.library)
    alias (libs.plugins.kotlin.android)
    alias (libs.plugins.kotlin.kapt)
    alias (libs.plugins.dagger.hilt.plugin)
}

android {
    namespace = "com.mashup.hongsam.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles ("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility (JavaVersion.VERSION_1_8)
        targetCompatibility (JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation (project(":domain"))
    implementation (libs.core.ktx)
    implementation (libs.appcompat)
    implementation (libs.material)
    implementation (libs.bundles.squareup.okhttp)
    implementation (libs.bundles.squareup.retrofit)
    implementation (libs.google.dagger)
    kapt (libs.google.dagger.compiler)
    implementation (libs.google.dagger.hilt)
    kapt (libs.google.dagger.hilt.compiler)
    testImplementation (libs.test.junit)
    androidTestImplementation (libs.test.ext.junit)
    androidTestImplementation (libs.test.espresso.core)
}
