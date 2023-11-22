Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
	id("com.android.application")
	id("kotlin-android")
	id("com.google.devtools.ksp")
	id("ch.ubique.gradle.ubdiag")
}

android {
	namespace = "ch.ubique.workshop"
	compileSdk = 34

	defaultConfig {
		minSdk = 26
		targetSdk = 34

		applicationId = "ch.ubique.workshop"
		versionName = "1.0.0"
		versionCode = 1_00_00_00

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		buildConfigField("String", "WEBSERVICE_BASE_URL", "\"https://workshop-mensa.s3.eu-west-1.amazonaws.com\"")
	}

	setFlavorDimensions(listOf("environment"))
	productFlavors {
		create("dev") {
			dimension = "environment"
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}

	kotlinOptions {
		jvmTarget = "11"
	}

	buildFeatures {
		buildConfig = true
		viewBinding = true
		compose = true
	}

	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.4"
	}
}

dependencies {
	// AndroidX
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("androidx.fragment:fragment-ktx:1.6.1")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
	implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

	// KotlinX
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

	// Compose
	implementation(platform("androidx.compose:compose-bom:2023.10.01")) // https://developer.android.com/jetpack/compose/setup#using-the-bom
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-tooling")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation("androidx.compose.foundation:foundation")
	implementation("androidx.compose.material3:material3:1.1.2")
	implementation("androidx.activity:activity-compose:1.8.1")
	implementation("io.coil-kt:coil-compose:2.5.0")

	// Networking
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
	implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
	implementation("com.squareup.moshi:moshi-adapters:1.15.0")
	ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")


	// Test
	testImplementation("androidx.test.ext:junit-ktx:1.1.5")
	androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
}