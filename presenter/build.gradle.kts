import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.mashup.twotoo.presenter"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        manifestPlaceholders["NATIVE_APP_KEY"] = getApiKey("native_app_key")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${rootProject.file(".").absolutePath}/compose-metrics",
        )
        freeCompilerArgs += listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${rootProject.file(".").absolutePath}/compose-reports",
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(libs.core.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.kotlin.collection.immutable)
    implementation(libs.bundles.androidx.compose)
    implementation(libs.bundles.squareup.okhttp)
    implementation(libs.bundles.squareup.retrofit)
    implementation(libs.google.dagger)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.kakao.login)
    implementation(libs.landscapist.glide)
    implementation(libs.bundles.pager)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.image.cropper)
    implementation(libs.androidx.compose.matieral3)
    kapt(libs.google.dagger.compiler)
    implementation(libs.bundles.orbit)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit)
    debugImplementation(libs.bundles.androidx.compose.ui.debug)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    androidTestImplementation(libs.test.navigation)
    androidTestImplementation(libs.truth)
    testImplementation(libs.turbine)
    testImplementation(libs.orbit.test)
    testImplementation(libs.kotlin.coroutine.test)
    implementation(libs.accompanist.webview)
    implementation(libs.moshi.kotlin)
    implementation(libs.bottomsheetdialog)
    implementation(libs.compose.keyboard.state)
    implementation(libs.lottie)
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey) ?: ""
}
