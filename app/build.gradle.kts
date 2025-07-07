plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.somnionocte.somnioui"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.somnionocte.somnioui"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2025.06.01"))
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.1")

    implementation(project(":somnio-design"))

    implementation("com.github.SomnioNocte:preferences-store:0.1.1")
    implementation("com.github.SomnioNocte:compose-extensions:0.1.1")
    implementation("com.github.SomnioNocte:overscroll:0.1.0")
    implementation("com.github.SomnioNocte:screen-router:0.8.2")

    implementation("dev.chrisbanes.haze:haze:1.5.4")
    implementation("androidx.compose.material:material-icons-extended")
}