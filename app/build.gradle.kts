plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(AppConfig.compileSdk)

    defaultConfig {
        applicationId = "com.tunes.finder"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    packagingOptions {
        exclude("META-INF/notice.txt")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerVersion = Versions.kotlin
        kotlinCompilerExtensionVersion = Versions.kotlinExt
    }
}

dependencies {
    //std lib
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //app libs
    implementation(AppDependencies.appcompat)
    implementation(AppDependencies.coreKtx)
    implementation(AppDependencies.constraintLayout)
    implementation(AppDependencies.material)
    implementation(AppDependencies.composeUi)
    implementation(AppDependencies.composeTooling)
    implementation(AppDependencies.composeFoundation)
    implementation(AppDependencies.composeMaterial)
    implementation(AppDependencies.composeLivedata)
    implementation(AppDependencies.composeActivity)
    implementation(AppDependencies.okHttpInterceptor)
    implementation(AppDependencies.koin)
    implementation(AppDependencies.koinAndroid)
    implementation(AppDependencies.coroutines)
    implementation(AppDependencies.coroutinesAndroid)
    implementation(AppDependencies.retrofit)
    implementation(AppDependencies.retrofitMoshi)
    implementation(AppDependencies.moshi)
    kapt(AppDependencies.moshiCodegen)
    debugImplementation(AppDependencies.chucker)
    releaseImplementation(AppDependencies.chuckerNoOp)
    implementation(AppDependencies.coil)

    //test libs
    testImplementation(AppDependencies.junit)
    androidTestImplementation(AppDependencies.extJUnit)
    androidTestImplementation(AppDependencies.espressoCore)
    androidTestImplementation(AppDependencies.composeTest)
}