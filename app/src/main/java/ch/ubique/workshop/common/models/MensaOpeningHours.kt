package ch.ubique.workshop.common.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MensaOpeningHours(
	val title: String,
	val text: String,
)