package com.example.growyourday.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.growyourday.data.database.AppDatabase
import com.example.growyourday.data.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoDao = AppDatabase.getDatabase(application).todoDao()

    val todos: LiveData<List<Todo>> = todoDao.getAllTodos().asLiveData()


    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.insert(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.update(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.delete(todo)
        }
    }

    // 완료 여부 토글
    fun toggle(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            val updated = todo.copy(isDone = !todo.isDone)
            todoDao.update(updated)
        }
    }

    // 완료율 계산
    fun getCompletionRate(list: List<Todo>?): Float {
        if (list.isNullOrEmpty()) return 0f
        val doneCount = list.count { it.isDone }
        return doneCount.toFloat() / list.size
    }
}
