package com.example.growyourday.ui.components // 실제 패키지 경로

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier // Modifier를 import 해야 함
import androidx.compose.ui.unit.dp
import com.example.growyourday.data.model.Todo
import com.example.growyourday.viewmodel.TodoViewModel // ViewModel이 필요하다면

@Composable
fun TodoItemCard(
    todo: Todo,
    viewModel: TodoViewModel, // 체크박스 클릭 시 필요
    modifier: Modifier = Modifier // 1. modifier를 파라미터로 받을 수 있도록 추가합니다.
) {
    Card(
        modifier = modifier // 2. 전달받은 modifier를 Card에 적용합니다.
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.isDone,
                onCheckedChange = { viewModel.toggle(todo.id)}
            )
            Text(text = todo.title)
        }
    }
}
