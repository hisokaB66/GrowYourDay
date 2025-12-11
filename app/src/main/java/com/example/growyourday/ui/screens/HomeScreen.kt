package com.example.growyourday.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.growyourday.data.model.Todo
import com.example.growyourday.ui.components.GrowingTree
import com.example.growyourday.ui.components.TodoItemCard
import com.example.growyourday.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: TodoViewModel = viewModel()) {
    val todos by viewModel.todos.observeAsState(initial = emptyList())
    val completion = if (todos.isEmpty()) 0f else todos.count { it.isDone }.toFloat() / todos.size.toFloat()
    var newTodoText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            // --- 상단 영역 ---
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TopAppBar(
                    title = { Text("Grow Your Day") },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
                GrowingTree(progress = completion, modifier = Modifier.padding(bottom = 16.dp))

                Text(

                    text = "진행률: ${(completion * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )


                LinearProgressIndicator(
                    progress = { completion },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .padding(horizontal = 16.dp),
                    color = Color(0xFF800020),
                    trackColor = Color.LightGray.copy(alpha = 0.4f)
                )



                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        bottomBar = {

            Surface(shadowElevation = 8.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = newTodoText,
                        onValueChange = { newTodoText = it },
                        label = { Text("새로운 할 일") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            if (newTodoText.isNotBlank()) {
                                val newId = (todos.maxOfOrNull { it.id } ?: 0) + 1
                                val newTodo = Todo(id = newId, title = newTodoText, isDone = false)
                                viewModel.addTodo(newTodo)
                                newTodoText = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF800020),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "할 일 추가")
                    }
                }
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            items(items = todos, key = { it.id }) { todo ->
                TodoItemCard(
                    todo = todo,
                    viewModel = viewModel
                )
            }
        }
    }

}
