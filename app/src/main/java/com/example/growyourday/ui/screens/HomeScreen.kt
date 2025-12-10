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

    // ▼▼▼▼▼ 1. Scaffold를 사용하여 화면의 상단, 중간, 하단을 명확히 나눕니다. ▼▼▼▼▼
    Scaffold(
        topBar = {
            // --- 상단 영역 ---
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TopAppBar(
                    title = { Text("Grow Your Day") },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
                GrowingTree(progress = completion, modifier = Modifier.padding(bottom = 16.dp))

                // ▼▼▼▼▼ 바로 이 부분을 수정합니다. ▼▼▼▼▼

                // 1. 진행률 텍스트 표시 (예: "진행률: 75%")
                Text(
                    // (completion * 100).toInt()를 사용하여 소수점을 정수로 만듭니다.
                    text = "진행률: ${(completion * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyLarge, // 텍스트 스타일 지정
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // 2. 프로그레스 바 색상 변경 및 배치
                LinearProgressIndicator(
                    progress = { completion },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .padding(horizontal = 16.dp),
                    color = Color(0xFF800020), // 진행된 바의 색상을 와인색으로 지정
                    trackColor = Color.LightGray.copy(alpha = 0.4f) // 바의 배경(트랙) 색상을 연한 회색으로 지정
                )

                // ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

                // 상단 영역과 중간 영역 사이에 약간의 공간을 줍니다.
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        bottomBar = {
            // --- 하단 영역 ---
            // 2-1. 키보드와 함께 올라오는 '할 일 추가' UI
            Surface(shadowElevation = 8.dp) { // 하단 바에 약간의 그림자 효과 추가
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
        // --- 중간 영역 ---
        // 3-1. 스크롤 가능한 할 일 목록
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), // 상단 바와 하단 바의 높이를 제외한 영역만 차지
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
    // ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲
}
