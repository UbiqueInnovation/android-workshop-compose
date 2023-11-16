pluginManagement {
	fun RepositoryHandler.ubique() = maven {
		name = "ubique"
		val ubiqueMavenUrl = System.getenv("UB_ARTIFACTORY_URL_ANDROID") ?: extra["ubiqueMavenUrl"] as? String ?: ""
		val ubiqueMavenUser = System.getenv("UB_ARTIFACTORY_USER") ?: extra["ubiqueMavenUser"] as? String ?: ""
		val ubiqueMavenPass = System.getenv("UB_ARTIFACTORY_PASSWORD") ?: extra["ubiqueMavenPass"] as? String ?: ""
		url = uri(ubiqueMavenUrl)
		credentials {
			username = ubiqueMavenUser
			password = ubiqueMavenPass
		}
		authentication {
			create<BasicAuthentication>("basic")
			create<DigestAuthentication>("digest")
		}
		content {
			includeGroupByRegex("ch\\.ubique\\..+")
		}
	}

	resolutionStrategy {
		eachPlugin {
			when (requested.id.id) {
				"ch.ubique.gradle.ubdiag" -> useModule("ch.ubique.gradle:ubdiag-android:${requested.version}")
				"ch.ubique.gradle.deusex" -> useModule("ch.ubique.gradle:deus-ex-android:${requested.version}")
				"ch.ubique.i18n" -> useModule("ch.ubique.i18n:poeditor-android:${requested.version}")
			}
		}
	}

	repositories {
		ubique()
		gradlePluginPortal()
		google()
		mavenCentral()
	}

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			ubique()
			google()
			mavenCentral()
		}
	}
}

include(":app")
