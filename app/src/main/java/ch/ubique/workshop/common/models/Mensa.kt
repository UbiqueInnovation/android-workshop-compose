package ch.ubique.workshop.common.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Mensa(
	val mensaId: Int,
	val name: String,
	val subName: String? = null,
	val imageUrl: String,
	val menuTime: MealTime,
	val menuDayDate: String? = null,
	val currentOpeningTime: String? = null,
	val address: String,
	val phone: String,
	val mail: String,
	val contact: String,
	val open: List<MensaOpeningHours>,
	val menus: List<MensaMenu>,
)
