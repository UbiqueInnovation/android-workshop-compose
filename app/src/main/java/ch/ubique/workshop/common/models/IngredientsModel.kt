package ch.ubique.workshop.common.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientsModel(
	val allergene: List<MealAllergen>,
	val additives: List<String>,
	val nutritionalValues: List<NutritionValue>,
)