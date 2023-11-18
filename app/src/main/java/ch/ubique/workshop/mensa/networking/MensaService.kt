package ch.ubique.workshop.mensa.networking

import ch.ubique.workshop.common.models.MensaList
import retrofit2.http.GET

interface MensaService {

	@GET("mensen.json")
	suspend fun getMensaList(): MensaList

}