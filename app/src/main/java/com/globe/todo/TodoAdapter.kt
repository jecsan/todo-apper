package com.globe.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var items: ArrayList<Todo>? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = items!!.get(position)
        holder.todo.text = todo.task

    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class TodoViewHolder(itemView: View) : ViewHolder(itemView) {
        lateinit var todo: CheckBox
        lateinit var ibRemove: ImageButton

        init {
            todo = itemView.findViewById(R.id.tvTask)
            ibRemove = itemView.findViewById(R.id.ibRemove)

            ibRemove.setOnClickListener {
                runCatching {
                    items?.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }.onFailure {
                    it.printStackTrace()
                }

            }
        }


    }
}