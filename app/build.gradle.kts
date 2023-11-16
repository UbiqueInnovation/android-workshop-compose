Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
	id("com.android.application")
	id("kotlin-android")
	id("ch.ubique.gradle.ubdiag")
	id("ch.ubique.gradle.deusex")
	id("ch.ubique.i18n")
}

android {
	namespace = "ch.ubique.templateandroid"
	compileSdk = 34

	defaultConfig {
		minSdk = 26
		targetSdk = 34

		applicationId = "ch.ubique.templateandroid"
		versionName = "1.0.0"
		versionCode = 1_00_00_00

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	productFlavors {
		getByName("dev") {

		}
		getByName("prod") {

		}
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}

	buildFeatures {
		viewBinding = true
		compose = true
	}

	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.4"
	}
}

poeditor {
	val poEditorApiKey = System.getenv("UBIQUE_POEDITOR_API_KEY") ?: extra["ubiquePoEditorAPIKey"] as? String ?: ""
	apiKey = poEditorApiKey
	projectId = "123456"
	defaultLang = "de"
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

	// Ubique
	implementation("ch.ubique.android:utils-kt:1.21")
	implementation("ch.ubique.android:compose-utils:1.1.0")
	implementation("ch.ubique.android:network:2.2.2")
	devImplementation("ch.ubique.android:devtools:1.1.0")
	prodImplementation("ch.ubique.android:devtools-noop:1.1.0")

	// Test
	testImplementation("androidx.test.ext:junit-ktx:1.1.5")
	androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
}