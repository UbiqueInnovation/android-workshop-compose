package ch.ubique.workshop.common.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.twotone.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.ubique.workshop.R

@Preview(showBackground = true)
@Composable
private fun TextPreview() {
	Text(
		text = "Hello World",
		color = Color.Red,
		fontSize = 20.sp,
		fontWeight = FontWeight.Bold,
		fontStyle = FontStyle.Italic
	)
}

@Preview(showBackground = true)
@Composable
private fun IconVectorPreview() {
	Icon(
		imageVector = Icons.Default.CheckCircle,
		contentDescription = "Check Circle",
		tint = Color.Blue,
	)
}

@Preview(showBackground = true)
@Composable
private fun IconResourcePreview() {
	Icon(
		painter = painterResource(R.drawable.ic_android_logo),
		contentDescription = "Android Logo",
		tint = Color.Green,
		modifier = Modifier.size(64.dp)
	)
}

@Preview(showBackground = true)
@Composable
private fun ImagePreview() {
	Image(
		imageVector = Icons.TwoTone.AddCircle,
		contentDescription = "Add Circle"
	)
}

@Preview(showBackground = true)
@Composable
private fun CircularProgressIndicatorPreview() {
	CircularProgressIndicator(progress = 0.75f)
}

@Preview(showBackground = true)
@Composable
private fun LinearProgressIndicatorPreview() {
	LinearProgressIndicator(progress = 0.75f)
}

@Preview(showBackground = true)
@Composable
private fun BoxPreview() {
	Box {
		Icon(
			imageVector = Icons.Default.Home,
			contentDescription = "Home",
			tint = Color.DarkGray
		)
		Text(text = "Hello Box")
	}
}

@Preview(showBackground = true)
@Composable
private fun ColumnPreview() {
	Column {
		Icon(
			imageVector = Icons.Default.Home,
			contentDescription = "Home",
			tint = Color.DarkGray
		)
		Text(text = "Hello Box")
	}
}

@Preview(showBackground = true)
@Composable
private fun RowPreview() {
	Row {
		Icon(
			imageVector = Icons.Default.Home,
			contentDescription = "Home",
			tint = Color.DarkGray
		)
		Text(text = "Hello Box")
	}
}

@Preview(showBackground = true)
@Composable
private fun StatefulComposablePreview() {
	val isExpanded = remember { mutableStateOf(false) }
	Row(Modifier.clickable { isExpanded.value = !isExpanded.value }) {
		Icon(
			imageVector = if (isExpanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
			contentDescription = "Arrow Down",
		)
		Text(text = if (isExpanded.value) "Click me to collapse" else "Click me to expand")
	}
}

@Preview(showBackground = true)
@Composable
private fun MyComponentPreview() {
	MyComponent()
}

@Composable
private fun MyComponent() {
	val counter = remember { mutableStateOf(0) }
	Button(onClick = { counter.value++ }) {
		MyText(text = "I've been clicked ${counter.value} times")
	}
}

@Composable
private fun MyText(text: String) {
	Text(text = text, Modifier.padding(8.dp))
}
