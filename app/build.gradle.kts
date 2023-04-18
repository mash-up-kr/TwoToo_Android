@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias (libs.plugins.android.application)
    alias (libs.plugins.kotlin.android)
}

android {
    namespace = "com.mashup.hongsam"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mashup.hongsam"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation (project(":presenter"))
    implementation (project(":data"))
    implementation (project(":domain"))
    implementation (libs.core.ktx)
    implementation (libs.appcompat)
    implementation (libs.material)
    testImplementation (libs.test.junit)
    androidTestImplementation (libs.test.ext.junit)
    androidTestImplementation (libs.test.espresso.core)
}