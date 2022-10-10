package com.globe.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoRv: RecyclerView
    private lateinit var etInput: EditText
    private lateinit var ibAdd: ImageButton

    private lateinit var adapter: TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoRv = findViewById(R.id.rvTodo)
        etInput = findViewById(R.id.etInput)
        ibAdd = findViewById(R.id.ibAdd)

        val todoSource = TodoSource()

        adapter = TodoAdapter()

        adapter.items = todoSource.getItems()

        todoRv.adapter = adapter


        ibAdd.setOnClickListener {
            val newTodo = etInput.text.toString().trim()
            if(newTodo.isNotEmpty()){
                todoSource.addTodo(newTodo)
                adapter.items = todoSource.getItems()

            }
        }

    }
}