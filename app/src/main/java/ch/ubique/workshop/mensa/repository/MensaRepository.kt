package ch.ubique.workshop.mensa.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import ch.ubique.workshop.mensa.networking.MensaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MensaRepository(
	private val preferences: SharedPreferences,
	private val service: MensaService,
) {

	companion object {
		private const val KEY_FAVORITE_MENSA_IDS = "KEY_FAVORITE_MENSA_IDS"
	}

	suspend fun getFavoriteMensaIds() = withContext(Dispatchers.IO) {
		preferences.getStringSet(KEY_FAVORITE_MENSA_IDS, emptySet())?.mapNotNull { it.toIntOrNull() } ?: emptyList()
	}

	suspend fun toggleFavoriteMensaId(mensaId: Int) = withContext(Dispatchers.IO) {
		val currentFavoriteMensaIds = getFavoriteMensaIds()
		val updatedFavoriteMensaIds = if (currentFavoriteMensaIds.contains(mensaId)) {
			currentFavoriteMensaIds.minus(mensaId)
		} else {
			currentFavoriteMensaIds.plus(mensaId)
		}
		preferences.edit {
			putStringSet(KEY_FAVORITE_MENSA_IDS, updatedFavoriteMensaIds.map { it.toString() }.toSet())
		}

		return@withContext updatedFavoriteMensaIds
	}

	suspend fun getMensaList() = withContext(Dispatchers.IO) {
		delay((0L..1000L).random()) // Simulate a slow network response
		service.getMensaList()
	}

}