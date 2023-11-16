package ch.ubique.templateandroid.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ch.ubique.templateandroid.common.compose.theme.TemplateAndroidTheme

@Composable
fun Greeting(name: State<String>, modifier: Modifier = Modifier) {
	Text(
		text = "Hello ${name.value}!",
		modifier = modifier
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	TemplateAndroidTheme {
		val name = remember { mutableStateOf("Android") }
		Greeting(name)
	}
}