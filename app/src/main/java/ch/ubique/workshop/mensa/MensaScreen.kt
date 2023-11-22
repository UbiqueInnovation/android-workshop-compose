package ch.ubique.workshop.mensa

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ch.ubique.workshop.common.models.Mensa
import ch.ubique.workshop.common.models.MensaMenu
import ch.ubique.workshop.common.util.ViewState
import coil.compose.AsyncImage

@Composable
fun MensaScreen(
	mensenState: State<ViewState<List<Mensa>>>,
	favoriteMensaIds: State<List<Int>>,
	onToggleMensaFavorite: (mensaId: Int) -> Unit,
	onRetryLoadingClicked: () -> Unit,
) {
	val mensen = mensenState.value
	AnimatedContent(targetState = mensen, label = "viewState") { state ->
		when (state) {
			is ViewState.Loading -> Box(Modifier.fillMaxSize()) {
				CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
			}
			is ViewState.Success -> MensaList(state.data, favoriteMensaIds, onToggleMensaFavorite)
			is ViewState.Error -> LoadingError(state.exception, onRetryLoadingClicked)
		}
	}
}

@Composable
private fun MensaList(
	mensen: List<Mensa>,
	favoriteMensaIds: State<List<Int>>,
	onToggleMensaFavorite: (mensaId: Int) -> Unit,
) {
	//TODO 4 Übung 4 Erstelle eine Variable (expandedMensaId), welche die expanded Mensa Id speichert

	// This kind of computation should normally be done in the ViewModel, but for the sake of simplicity it is done here
	val sortedMensen = mensen.sortedByDescending { favoriteMensaIds.value.contains(it.mensaId) }

	val lazyListState = rememberLazyListState()
	LazyColumn(
		state = lazyListState,
		modifier = Modifier.fillMaxSize()
	) {
		sortedMensen.forEach { mensa ->
			item(key = mensa.mensaId) {
				val isFavorite = favoriteMensaIds.value.contains(mensa.mensaId)
				//TODO 4 Übung 4 Prüfe, ob die Mensa expanded ist oder nicht
				val isExpanded = false
				MensaItem(
					mensa = mensa,
					isFavorite = isFavorite,
					isExpanded = isExpanded,
					onMensaClicked = {
						//TODO 4 Übung 4 Setze die expandedMensaId auf die mensaId, wenn die Mensa nicht expanded ist
					},
					onToggleFavoriteClicked = {
						onToggleMensaFavorite.invoke(mensa.mensaId)
					},
				)
			}
		}
	}
}

@Composable
private fun LoadingError(
	exception: Exception,
	onRetryClicked: () -> Unit,
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(horizontal = 20.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red, modifier = Modifier.size(48.dp))
		Spacer(Modifier.height(10.dp))
		Text("Fehler beim laden", style = MaterialTheme.typography.headlineMedium)
		Spacer(Modifier.height(10.dp))
		Text("Etwas ist schiefgelaufen. Bitte probiere es erneut.", style = MaterialTheme.typography.bodyMedium)
		Text("Fehler: ${exception.javaClass.simpleName}", style = MaterialTheme.typography.bodyMedium)
		Spacer(Modifier.height(10.dp))
		Button(onClick = onRetryClicked) {
			Text("Erneut probieren")
		}
	}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyItemScope.MensaItem(
	mensa: Mensa,
	isFavorite: Boolean,
	isExpanded: Boolean,
	onMensaClicked: () -> Unit,
	onToggleFavoriteClicked: () -> Unit,
) {
	Column(
		//TODO 4 Übung 4 fügen den clickable modifier hinzu, welcher die onMensaClicked Funktion aufruft
		modifier = Modifier
			.background(Color.White)
			.fillMaxWidth()
			.animateItemPlacement()
	) {

		Row(
			modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
		) {
			//TODO 5 Übung 5 Zeige das Mensa Bild an
			//https://coil-kt.github.io/coil/compose/
			Spacer(Modifier.width(10.dp))
			Column(Modifier.weight(1f)) {
				Text(mensa.name, style = MaterialTheme.typography.titleLarge)
				mensa.subName?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
				Text(mensa.address, style = MaterialTheme.typography.bodyMedium)
			}
			Spacer(Modifier.width(10.dp))
			//TODO 6 Übung 6 Fügen ein Icon hinzu, welches anzeigt, ob die Mensa als Favorit markiert ist oder nicht.
		}

		//TDOO 4 Übung 4 Zeige das Menu an, wenn die Mensa expanded ist. Benutze dazu AnimatedVisibility

		Divider(color = Color.LightGray)
	}
}

@Composable
private fun MensaMenuItem(
	menu: MensaMenu,
) {
	Column(Modifier.padding(vertical = 5.dp)) {
		Row(verticalAlignment = Alignment.CenterVertically) {
			Text(menu.menuTitle, style = MaterialTheme.typography.titleMedium)
			Spacer(Modifier.weight(1f))
			if (menu.priceExtern > 0f) {
				Text("CHF ${menu.priceExternText}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
			}
		}
		Text(menu.menuText, style = MaterialTheme.typography.bodySmall)
	}
}