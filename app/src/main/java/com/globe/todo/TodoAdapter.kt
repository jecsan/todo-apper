package com.globe.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    //Call this function when a TODO is removed
    var onItemDeleted: ((position: Int) -> Unit)? = null
    var onItemUpdated: ((position: Int) -> Unit)? = null

    var todos: ArrayList<Todo>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    fun addTodo(todo: Todo) {
        todos?.add(todo)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos!![position]
        holder.todoCheckBox.text = todo.task
        holder.todoCheckBox.isChecked = todo.done

        if (todo.done) {
            holder.todoCheckBox.alpha = 0.7f
        } else {
            holder.todoCheckBox.alpha = 1f
        }
    }

    override fun getItemCount(): Int {
        return todos?.size ?: 0
    }

    inner class TodoViewHolder(itemView: View) : ViewHolder(itemView) {
        lateinit var todoCheckBox: CheckBox
        lateinit var ibRemove: ImageButton

        init {
            todoCheckBox = itemView.findViewById(R.id.tvTask)
            ibRemove = itemView.findViewById(R.id.ibRemove)

            todoCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                val todo = todos?.get(adapterPosition)
                todo?.done = isChecked

                if (todo != null) {
                    runCatching {
                        todos?.set(adapterPosition, todo)
                        onItemUpdated?.invoke(adapterPosition)
                        notifyItemChanged(adapterPosition)
                    }
                }
            }

            ibRemove.setOnClickListener {

                runCatching {
                    //Pass the position of item to be remove to
                    //MainActivity
                    onItemDeleted?.invoke(adapterPosition)
                    todos?.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }.onFailure {
                    it.printStackTrace()
                }

            }
        }


    }
}