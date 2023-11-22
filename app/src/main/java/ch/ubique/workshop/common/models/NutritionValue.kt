package ch.ubique.workshop.common.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NutritionValue(
	val name: String,
	val value: String,
)