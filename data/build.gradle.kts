
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "${Config.namespace}.data"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //Modules
    implementation(project(mapOf("path" to ":domain")))

    implementation(DataDependencies.Room.ktx)
    implementation(DataDependencies.Room.paging)
    implementation(DataDependencies.Room.runtime)
    implementation("androidx.core:core-ktx:+")
    kapt(DataDependencies.Room.compiler)

    implementation(DataDependencies.SquareUp.okhttp)
    implementation(DataDependencies.SquareUp.retrofit)
    implementation(DataDependencies.SquareUp.converter)
    implementation(DataDependencies.SquareUp.interceptor)


    testImplementation(CommonDependencies.Test.jUnit)
    androidTestImplementation(CommonDependencies.Test.androidJUnit)
}