package ch.ubique.templateandroid.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

	private val greetingMutable = MutableStateFlow("")
	val greeting = greetingMutable.asStateFlow()

	fun loadGreeting() {
		greetingMutable.value = "Android"
	}
}