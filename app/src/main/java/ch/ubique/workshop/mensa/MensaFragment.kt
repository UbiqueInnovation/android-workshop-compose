package ch.ubique.workshop.mensa

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ch.ubique.workshop.BuildConfig
import ch.ubique.workshop.common.compose.theme.TemplateAndroidTheme
import ch.ubique.workshop.databinding.FragmentComposeBinding
import ch.ubique.workshop.mensa.networking.MensaService
import ch.ubique.workshop.mensa.repository.MensaRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MensaFragment : Fragment() {

	companion object {
		private const val PREF_NAME_MENSA = "prefs_mensa"

		fun newInstance() = MensaFragment()
	}

	private var _binding: FragmentComposeBinding? = null
	private val binding get() = _binding!!

	private val viewModel by viewModels<MensaViewModel> {
		val preferences = requireContext().getSharedPreferences(PREF_NAME_MENSA, Context.MODE_PRIVATE)
		val httpClient = OkHttpClient.Builder().build()
		val retrofit = Retrofit.Builder()
			.client(httpClient)
			.baseUrl(BuildConfig.WEBSERVICE_BASE_URL)
			.addConverterFactory(MoshiConverterFactory.create())
			.build()
		val service = retrofit.create(MensaService::class.java)
		val repository = MensaRepository(preferences, service)
		MensaViewModel.Factory(repository)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel.loadMensen()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentComposeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.composeView.setContent {
			TemplateAndroidTheme {
				MensaScreen(
					mensenState = viewModel.mensen.collectAsState(),
					favoriteMensaIds = viewModel.favoriteMensaIds.collectAsState(),
					onToggleMensaFavorite = { mensaId ->
						viewModel.toggleMensaFavorite(mensaId)
					},
					onRetryLoadingClicked = {
						viewModel.loadMensen()
					}
				)
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}