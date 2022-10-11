package com.globe.todo

// ViewModel
// Room + SQL
class TodoSource {

    fun getItems(): ArrayList<Todo> {
        return todos
    }

    fun addTodo(newTodo: String) {
        todos.add(Todo(0, newTodo))
    }

    private val todos = ArrayList<Todo>()

    init {
        //Predefined list of todos, 10 items minimum
    }
}