plugins {
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.somnionocte.somnio_design"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2025.06.01"))
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.ui:ui")
    
    implementation("com.github.SomnioNocte:atomic-components:0.2.3")
    implementation("com.github.SomnioNocte:portal:0.1.0")
    implementation("com.github.SomnioNocte:overscroll:0.1.0")
    implementation("com.github.SomnioNocte:compose-extensions:0.1.1")

    implementation("com.github.ajalt.colormath:colormath-ext-jetpack-compose:3.6.1")
    implementation("dev.chrisbanes.haze:haze:1.5.4")
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity.compose)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.somnionocte"
                artifactId = "somnio-design"
                version = "0.0.1"
                from(components["release"])
            }
        }
    }
}