package com.example.growyourday.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.growyourday.data.model.Todo
import com.example.growyourday.ui.components.TodoItemCard
import com.example.growyourday.ui.components.TreeProgressBar
import com.example.growyourday.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: TodoViewModel = viewModel()) {

    // LiveData 기반 todos
    val todos by viewModel.todos.observeAsState(initial = emptyList())

    // 완료율
    val completion = viewModel.getCompletionRate()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Grow Your Day") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 프로그래스바
            TreeProgressBar(progress = completion)
            Spacer(modifier = Modifier.height(16.dp))

            // 할 일 리스트
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = todos, key = { it.id }) { todo: Todo ->
                    TodoItemCard(
                        todo = todo,
                        viewModel = viewModel // viewModel은 체크박스 때문에 필요할 수 있어 남겨둡니다.
                    )
                }
            }
        }
    }
}
