package ch.ubique.workshop.common.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MensaMenu(
	val mealDate: String? = null,
	val mealTime: MealTime? = null,
	val priceStudent: Float,
	val priceStudentText: String,
	val priceEmployee: Float,
	val priceEmployeeText: String,
	val priceExtern: Float,
	val priceExternText: String,
	val menuTitle: String,
	val menuText: String,
	val menuTypes: List<MenuType>,
	val ingredients: IngredientsModel? = null,
)