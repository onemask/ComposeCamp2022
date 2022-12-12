package com.codelabs.basicstatecodelab.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
  Column(modifier = modifier.padding(16.dp)) {
    var count by remember { mutableStateOf(0) }
    if (count > 0) {
      Text("You've had $count glasses.")
    }
    Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
      Text("Add one")
    }
  }}

@Composable
fun WellnessScreen() {
  StatefulCounter()
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
  Column(modifier = modifier.padding(16.dp)) {
    if (count > 0) {
      Text("You've had $count glasses.")
    }
    Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
      Text("Add one")
    }
  }
}

@Composable
fun StatefulCounter() {
  var count by remember { mutableStateOf(0) }

  StatelessCounter(count, { count++ })
}

@Composable
fun WellnessTaskItem(
  taskName: String, onClose: () -> Unit, modifier: Modifier = Modifier
) {
  var checkedState by rememberSaveable { mutableStateOf(false) }

  WellnessTaskItem(
    taskName = taskName,
    checked = checkedState,
    onCheckedChange = { newValue -> checkedState = newValue },
    onClose = onClose,
    modifier = modifier,
  )
}


data class WellnessTask(val id: Int, val label: String)

@Composable
fun WellnessTasksList(
  list: List<WellnessTask>,
  onCloseTask: (WellnessTask) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(modifier = modifier) {
    items(
      items = list,
      key = { task -> task.id }
    ) { task ->
      WellnessTaskItem(taskName = task.label, onClose = { onCloseTask(task) })
    }
  }
}
@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    StatefulCounter()

    val list = remember { getWellnessTasks().toMutableStateList() }
    WellnessTasksList(list = list, onCloseTask = { task -> list.remove(task) })
  }
}


