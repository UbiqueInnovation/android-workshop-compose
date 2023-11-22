package ch.ubique.workshop.common.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class MealTime {
	LUNCH,
	DINNER,
}