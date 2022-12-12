package com.codelabs.basicstatecodelab.ui.theme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
  val count = 0
  Text(
    text = "You've had $count glasses.",
    modifier = modifier.padding(16.dp)
  )
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
  WaterCounter(modifier)
}