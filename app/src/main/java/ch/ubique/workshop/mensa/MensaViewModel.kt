package ch.ubique.workshop.mensa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ch.ubique.workshop.common.models.Mensa
import ch.ubique.workshop.common.util.ViewState
import ch.ubique.workshop.mensa.repository.MensaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MensaViewModel(
	private val repository: MensaRepository,
) : ViewModel() {

	private val mensenMutable = MutableStateFlow<ViewState<List<Mensa>>>(ViewState.Loading)
	val mensen = mensenMutable.asStateFlow()

	private val favoriteMensaIdsMutable = MutableStateFlow<List<Int>>(emptyList())
	val favoriteMensaIds = favoriteMensaIdsMutable.asStateFlow()

	init {
		viewModelScope.launch {
			favoriteMensaIdsMutable.value = repository.getFavoriteMensaIds()
		}
	}

	fun loadMensen() {
		viewModelScope.launch {
			mensenMutable.value = ViewState.Loading
			try {
				val mensaList = repository.getMensaList()
				mensenMutable.value = ViewState.Success(mensaList.mensa)
			} catch (e: Exception) {
				mensenMutable.value = ViewState.Error(e)
			}
		}
	}

	fun toggleMensaFavorite(mensaId: Int) {
		viewModelScope.launch {
			favoriteMensaIdsMutable.value = repository.toggleFavoriteMensaId(mensaId)
		}
	}

	class Factory(
		private val repository: MensaRepository,
	) : ViewModelProvider.Factory {
		@Suppress("UNCHECKED_CAST")
		override fun <T : ViewModel> create(modelClass: Class<T>): T {
			return MensaViewModel(repository) as T
		}
	}
}