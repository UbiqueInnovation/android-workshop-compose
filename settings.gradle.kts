pluginManagement {

	resolutionStrategy {
		eachPlugin {
			when (requested.id.id) {
				"ch.ubique.gradle.ubdiag" -> useModule("ch.ubique.gradle:ubdiag-android:${requested.version}")
			}
		}
	}

	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			google()
			mavenCentral()
		}
	}
}

include(":app")
