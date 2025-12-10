package com.example.growyourday.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.growyourday.data.model.Todo
import com.example.growyourday.viewmodel.TodoViewModel

@Composable
fun TodoItemCard(
    todo: Todo,
    viewModel: TodoViewModel, // ViewModel을 직접 참조하는 원래 방식으로 복구
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF0F5) // newapp의 카드 배경색 적용
        )
    ) {
        Row(
            modifier = Modifier.padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.isDone,
                onCheckedChange = { viewModel.toggle(todo.id) },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black,
                    uncheckedColor = Color.Black,
                    checkmarkColor = Color.White
                )
            )
            Text(
                text = todo.title,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                // ViewModel의 deleteTodo 함수를 직접 호출
                onClick = { viewModel.deleteTodo(todo) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "할 일 삭제"
                )
            }
        }
    }
}
