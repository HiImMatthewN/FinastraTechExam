import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")

}

val keystorePropertiesFile = rootProject.file("local.properties")
val localProps = Properties()
localProps.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "com.nantes.matthew.finastratechexam"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nantes.matthew.finastratechexam"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {

        debug {
            buildConfigField("String", "BASE_URL", localProps.getProperty("BASE_URL"))
            buildConfigField("String", "API_KEY", localProps.getProperty("API_KEY"))
        }

        release {
            buildConfigField("String", "BASE_URL", localProps.getProperty("BASE_URL"))
            buildConfigField("String", "API_KEY", localProps.getProperty("API_KEY"))
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
        buildConfig = true
    }
}

dependencies {

    //Dependency Injection
    val hiltVersion = "1.1.0"
    val daggerHiltVersion = "2.50"

    implementation("com.google.dagger:hilt-android:$daggerHiltVersion")
    ksp("com.google.dagger:hilt-compiler:$daggerHiltVersion")
    implementation("androidx.hilt:hilt-common:$hiltVersion")
    ksp("androidx.hilt:hilt-compiler:$hiltVersion")

    //Network
    val okhttpVersion = "4.12.0"
    val retrofitVersion = "2.9.0"
    val gsonVersion = "2.9.0"

    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    //UI
    val navVersion = "2.7.6"
    val glideVersion = "4.16.0"
    val materialVersion = "1.11.0"
    val androidCoreVersion = "1.12.0"
    val appCompatVersion = "1.6.1"
    val constraintLayoutVersion = "2.1.4"

    implementation("androidx.core:core-ktx:$androidCoreVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    ksp("com.github.bumptech.glide:ksp:$glideVersion")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.activity:activity-ktx:1.8.2")

    //Local
    val roomVersion = "2.6.1"

    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")


    //Testing
    val junitVersion = "4.13.2"
    val androidJUnitVersion = "1.1.5"
    val espressoVersion = "3.5.1"
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.test.ext:junit:$androidJUnitVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    implementation("com.makeramen:roundedimageview:2.3.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.github.valkriaine:Bouncy:2.3")

    //UI
    implementation ("androidx.core:core-splashscreen:1.0.1")



}