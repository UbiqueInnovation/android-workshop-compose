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
		//TODO 6 Übung 6: Implementiere die Funktion toggleFavoriteMensaId
		//Lese die Liste der favorisierten Mensen aus den SharedPreferences aus
		emptyList<Int>()
	}

	suspend fun toggleFavoriteMensaId(mensaId: Int) = withContext(Dispatchers.IO) {
		//TODO 6 Übung 6: Implementiere die Funktion toggleFavoriteMensaId
		//Update die Liste der favorisierten Mensen in den SharedPreferences
		return@withContext getFavoriteMensaIds()
	}

	suspend fun getMensaList() = withContext(Dispatchers.IO) {
		delay((0L..1000L).random()) // Simulate a slow network response
		service.getMensaList()
	}

}