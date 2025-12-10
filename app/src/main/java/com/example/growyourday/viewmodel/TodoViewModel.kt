package com.example.growyourday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.growyourday.data.model.Todo

class TodoViewModel : ViewModel() {

    // Todo 리스트 상태 (LiveData)
    private val _todos = MutableLiveData<List<Todo>>(emptyList())
    val todos: LiveData<List<Todo>> = _todos

    init {
        // 예시 데이터
        _todos.value = listOf(
            Todo(1, "할 일 1", false),
            Todo(2, "할 일 2", true),
            Todo(3, "할 일 3", false)
        )
    }

    // Todo 업데이트
    fun updateTodo(updatedTodo: Todo) {
        _todos.value = _todos.value?.map { if (it.id == updatedTodo.id) updatedTodo else it }
    }

    // Todo 추가
    fun addTodo(todo: Todo) {
        val currentList = _todos.value ?: emptyList()
        _todos.value = currentList + todo
    }

    // Todo 삭제
    fun deleteTodo(todo: Todo) {
        _todos.value = _todos.value?.filter { it.id != todo.id }
    }
    fun toggle(todoId: Int) {
        // 현재 목록에서 ID에 해당하는 할 일을 찾습니다.
        val todo = todos.value?.find { it.id == todoId } ?: return

        // isDone 값을 반전시킨 복사본을 만들고, 기존 updateTodo 함수를 호출합니다.
        updateTodo(todo.copy(isDone = !todo.isDone))
    }
    // 완료율 계산 (0.0 ~ 1.0)
    fun getCompletionRate(): Float {
        val list = _todos.value ?: return 0f
        if (list.isEmpty()) return 0f
        val doneCount = list.count { it.isDone }
        return doneCount.toFloat() / list.size
    }


}
