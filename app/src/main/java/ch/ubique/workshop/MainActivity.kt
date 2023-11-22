package ch.ubique.workshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ch.ubique.workshop.mensa.MensaFragment

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.activity_main)

		supportFragmentManager.commit {
			add(R.id.fragment_container, MensaFragment.newInstance())
		}
	}

}

