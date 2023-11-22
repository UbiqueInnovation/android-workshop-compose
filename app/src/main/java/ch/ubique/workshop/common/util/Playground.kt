package ch.ubique.workshop.common.util

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	Column {
		TextTest()
		IconTest()
	}
}