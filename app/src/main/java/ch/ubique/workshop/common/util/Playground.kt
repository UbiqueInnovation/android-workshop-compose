package ch.ubique.workshop.common.util

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun TextTest() {
	Text(text = "Hello World", color = Color.Red)
}

@Composable
fun IconTest() {
	Icon(
		imageVector = Icons.Default.CheckCircle,
		contentDescription = "Check Circle",
		tint = Color.Green
	)
}

@Composable
fun ImageTest() {
	Image(imageVector = Icons.Filled.AddCircle, contentDescription = "Add Circle")
}

@Composable
fun ButtonTest(onButtonClicked: () -> Unit) {
	Button(onClick = { onButtonClicked.invoke() }) {
		Text(text = "Click me")
	}
}

@Composable
fun ProgressIndicator() {
	CircularProgressIndicator()
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	Column {
		TextTest()
		IconTest()
		ImageTest()
		ButtonTest(onButtonClicked = {})
		ProgressIndicator()
	}
}