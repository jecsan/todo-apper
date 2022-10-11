package com.globe.todo

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var todoRv: RecyclerView
    private lateinit var etInput: EditText
    private lateinit var ibAdd: ImageButton

    private lateinit var adapter: TodoAdapter

    private val viewModel: TodoViewModel by viewModels { TodoViewModel.Factory }

    private fun setupViews() {
        todoRv = findViewById(R.id.rvTodo)
        etInput = findViewById(R.id.etInput)
        ibAdd = findViewById(R.id.ibAdd)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupAdapter()
        addEventListeners()

        viewModel.todosLiveData.observe(this) { todoList ->
            adapter.todos = ArrayList(todoList)
        }
    }

    private fun setupAdapter() {
        adapter = TodoAdapter()
        todoRv.adapter = adapter
    }

    private fun addEventListeners() {

        adapter.onItemDeleted = { position ->
            viewModel.deleteTodo(adapter.todos?.get(position))
        }

        adapter.onItemUpdated = { position ->
            viewModel.updateTodo(adapter.todos?.get(position))
        }

        ibAdd.setOnClickListener {
            val task = etInput.text.toString().trim()
            if (task.isNotEmpty()) {
                etInput.setText("")
                val newTodo = Todo(0, task)
                viewModel.addTodo(newTodo)
            }
        }
    }
}