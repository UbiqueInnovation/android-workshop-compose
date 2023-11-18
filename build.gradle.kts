// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
	id("com.android.application") version "8.1.2" apply false
	id("org.jetbrains.kotlin.android") version "1.9.20" apply false
	id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
	id("ch.ubique.gradle.ubdiag") version "8.1.0" apply false
}

tasks.register("clean", Delete::class) {
	delete(rootProject.layout.buildDirectory)
}
