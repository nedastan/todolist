package com.example.to_do_list_mad
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list_mad.model.Task

class HistoryAdapter(private var tasks: List<Task>, private val context: Context) : RecyclerView.Adapter<HistoryAdapter.TaskViewHolder>() {
    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemDelete(position: Int, task: Task?)
        fun onItemEdit(position: Int, task: Task?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.history_task_list_item, parent, false)
        return TaskViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.TaskViewHolder, position: Int) {
        holder.itemView.tag = tasks[position]
        val currentTask = tasks[position]
        holder.itemName.text = currentTask.taskName
        holder.date.text = currentTask.date
    }


    override fun getItemCount(): Int {
        return tasks.size
    }

    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView
        var date: TextView
        var delete: ImageView

        init {
            itemName = itemView.findViewById(R.id.history_item_name)
            date = itemView.findViewById(R.id.history_date)
            delete = itemView.findViewById(R.id.history_delete_task)
            delete.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemDelete(position, tasks[adapterPosition])
                        notifyItemRemoved(position)
                    }
                }
            }
        }
    }


}