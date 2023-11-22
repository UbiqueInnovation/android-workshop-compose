package ch.ubique.workshop.common.util

sealed interface ViewState<out T> {
	data object Loading : ViewState<Nothing>
	data class Success<T>(val data: T) : ViewState<T>
	data class Error(val exception: Exception) : ViewState<Nothing>
}