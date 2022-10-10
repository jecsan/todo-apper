package com.globe.todo

// ViewModel
// Room + SQL
class TodoSource {

    fun getItems(): ArrayList<Todo> {
        return todos
    }

    fun addTodo(newTodo: String) {
        todos.add(Todo(newTodo))
    }

    private val todos = ArrayList<Todo>()

    init {
        //Predefined list of todos, 10 items minimum
        todos.add(Todo("Clean the house"))
        todos.add(Todo("Walk the dog", true))
        todos.add(Todo("Walk the dog", true))
        todos.add(Todo("Walk the dog2", true))
        todos.add(Todo("Walk the dog3", true))
        todos.add(Todo("Walk the dog4", true))
        todos.add(Todo("Walk the dog5", true))
        todos.add(Todo("Walk the dog6", true))
        todos.add(Todo("Walk the dog7", true))
        todos.add(Todo("Walk the dog8", true))
        todos.add(Todo("Walk the dog9", true))
        todos.add(Todo("Walk the dog0", true))
    }
}