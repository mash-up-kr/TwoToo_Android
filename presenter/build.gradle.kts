@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias (libs.plugins.android.library)
    alias (libs.plugins.kotlin.android)
    alias (libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.mashup.twotoo.presenter"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }

}

dependencies {

    implementation (project(":domain"))
    implementation (libs.core.ktx)
    implementation (libs.androidx.lifecycle)
    implementation (libs.androidx.activity.compose)
    implementation (libs.bundles.androidx.compose)
    implementation (libs.bundles.squareup.okhttp)
    implementation (libs.bundles.squareup.retrofit)
    implementation (libs.google.dagger)
    implementation (libs.androidx.compose.navigation)
    kapt (libs.google.dagger.compiler)
    implementation (libs.bundles.orbit)
    testImplementation (libs.test.junit)
    androidTestImplementation (libs.test.ext.junit)
    androidTestImplementation (libs.test.espresso.core)
    androidTestImplementation (libs.androidx.compose.ui.test.junit)
    debugImplementation (libs.bundles.androidx.compose.ui.debug)

}
