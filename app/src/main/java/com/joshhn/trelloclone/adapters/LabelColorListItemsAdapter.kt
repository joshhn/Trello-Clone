package com.joshhn.trelloclone.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joshhn.trelloclone.databinding.ItemLabelColorBinding

class LabelColorListItemsAdapter(private val context: Context,
                                 private var list: ArrayList<String>,
                                 private var mSelectedColor: String)
    :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    class MyViewHolder(binding: ItemLabelColorBinding): RecyclerView.ViewHolder(binding.root){
        val viewMain = binding.viewMain
        val ivSelectedColor = binding.ivSelectedColor
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LabelColorListItemsAdapter.MyViewHolder(
            ItemLabelColorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]

        if (holder is MyViewHolder) {

            holder.viewMain.setBackgroundColor(Color.parseColor(item))

            if (item == mSelectedColor) {
                holder.ivSelectedColor.visibility = View.VISIBLE
            } else {
                holder.ivSelectedColor.visibility = View.GONE
            }

            holder.itemView.setOnClickListener {

                if (onItemClickListener != null) {
                    onItemClickListener!!.onClick(position, item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onClick(position: Int, color: String)
    }
}