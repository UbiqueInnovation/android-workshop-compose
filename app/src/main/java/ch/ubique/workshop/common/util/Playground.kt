package ch.ubique.workshop.common.util

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun TextTest() {
	Text(text = "Hello World", color = Color.Red)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	TextTest()
}