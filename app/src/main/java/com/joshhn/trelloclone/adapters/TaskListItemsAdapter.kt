package com.joshhn.trelloclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.joshhn.trelloclone.activities.TaskListActivity
import com.joshhn.trelloclone.databinding.ItemTaskBinding
import com.joshhn.trelloclone.models.Task

open class TaskListItemsAdapter(private val context: Context, private var list: ArrayList<Task>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class MyViewHolder(binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root){
        val tvAddTaskList = binding.tvAddTaskList
        val llTaskItem = binding.llTaskItem
        val tvTaskListTitle = binding.tvTaskListTitle
        val cvAddTaskListName = binding.cvAddTaskListName
        val ibCloseListName = binding.ibCloseListName
        val ibDoneListName = binding.ibDoneListName
        val etTaskListName = binding.etTaskListName
        val ibEditListName = binding.ibEditListName
        val etEditTaskListName = binding.etEditTaskListName
        val llTitleView = binding.llTitleView
        val cvEditTaskListName = binding.cvEditTaskListName
        val ibCloseEditableView = binding.ibCloseEditableView
        val ibDoneEditListName = binding.ibDoneEditListName
        val ibDeleteList = binding.ibDeleteList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TaskListItemsAdapter.MyViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if(holder is MyViewHolder){
            if(position == list.size - 1){
                holder.tvAddTaskList.visibility = View.VISIBLE
                holder.llTaskItem.visibility = View.GONE
            }else{
                holder.tvAddTaskList.visibility = View.GONE
                holder.llTaskItem.visibility = View.VISIBLE
            }
            holder.tvTaskListTitle.text = model.title
            holder.tvAddTaskList.setOnClickListener {
                holder.tvAddTaskList.visibility = View.GONE
                holder.cvAddTaskListName.visibility = View.VISIBLE
            }

            holder.ibCloseListName.setOnClickListener {
                holder.tvAddTaskList.visibility = View.VISIBLE
                holder.cvAddTaskListName.visibility = View.GONE
            }

            holder.ibDoneListName.setOnClickListener {
                val listName = holder.etTaskListName.text.toString()

                if(listName.isNotEmpty()){
                    if(context is TaskListActivity){
                        context.createTaskList(listName)
                    }
                }else{
                    Toast.makeText(context,"Please Enter a List name.", Toast.LENGTH_SHORT).show()
                }
            }

            holder.ibEditListName.setOnClickListener {
                holder.etEditTaskListName.setText(model.title)
                holder.llTitleView.visibility = View.GONE
                holder.cvEditTaskListName.visibility = View.VISIBLE
            }

            holder.ibCloseEditableView.setOnClickListener {
                holder.llTitleView.visibility = View.VISIBLE
                holder.cvEditTaskListName.visibility = View.GONE
            }

            holder.ibDoneEditListName.setOnClickListener {
                val listName = holder.etEditTaskListName.text.toString()
                if(listName.isNotEmpty()){
                    if(context is TaskListActivity){
                        context.updateTaskList(position,listName,model)
                    }
                }else{
                    Toast.makeText(context,"Please Enter a List name.", Toast.LENGTH_SHORT).show()
                }
            }

            holder.ibDeleteList.setOnClickListener {
                alertDialogForDeleteList(position, model.title)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

//    private fun Int.toDp(): Int =  (this / Resources.getSystem().displayMetrics.density).toInt()
//
//    private fun Int.toPx(): Int =  (this * Resources.getSystem().displayMetrics.density).toInt()

    private fun alertDialogForDeleteList(position: Int, title: String) {
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Alert")

        builder.setMessage("Are you sure you want to delete $title.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes") { dialogInterface, _ ->
            dialogInterface.dismiss() // Dialog will be dismissed

            if (context is TaskListActivity) {
                context.deleteTaskList(position)
            }
        }

        builder.setNegativeButton("No") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
}
}