package com.globe.todo

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch

//Store and Manage UI-related data in a lifecycle aware away
class TodoViewModel(private val db: TodoDb) : ViewModel() {

    //Observable pattern
    val todosLiveData: LiveData<List<Todo>> = db.todoDao().getAllFlow().asLiveData()

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            db.todoDao().insert(todo)
        }
    }

    fun deleteTodo(todo: Todo?) {
        if (todo != null) {
            viewModelScope.launch {
                db.todoDao().delete(todo)
            }
        }
    }

    fun updateTodo(todo: Todo?) {
        viewModelScope.launch {
            if (todo != null) {
                db.todoDao().update(todo)
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])

                return TodoViewModel(
                    (application as TodoApp).todoDb,
                ) as T
            }
        }
    }

}