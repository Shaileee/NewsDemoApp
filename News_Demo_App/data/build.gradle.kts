plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    namespace = "shaileedemo.project.data"
    compileSdk = appConfig.compileSdkVersion

    defaultConfig {
        minSdk = appConfig.minSdkVersion
        targetSdk = appConfig.targetSdkVersion

        buildConfigField("String", "API_KEY", "\"e14f799c479b48bfb91ad7e0dceb7853\"") // TODO you must get net token from https://newsapi.org/

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
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
}

dependencies {
    implementation(domain)
    implementation(common)

    implementation(deps.coroutines.core)

    implementation(deps.squareup.retrofit)
    implementation(deps.squareup.gsonConverter)
    implementation(deps.squareup.gson)
    implementation(deps.squareup.loggingInterceptor)

    implementation(deps.koin.core)

    addUnitTest()
}