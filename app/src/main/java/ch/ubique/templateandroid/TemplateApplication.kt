package ch.ubique.templateandroid

import android.app.Application
import ch.ubique.devtools.DevTools

class TemplateApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		DevTools.setup(this, BuildConfig.IS_FLAVOR_DEV)
	}
}