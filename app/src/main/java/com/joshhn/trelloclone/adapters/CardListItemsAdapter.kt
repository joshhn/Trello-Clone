package com.joshhn.trelloclone.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joshhn.trelloclone.activities.TaskListActivity
import com.joshhn.trelloclone.databinding.ItemCardBinding
import com.joshhn.trelloclone.databinding.ItemTaskBinding
import com.joshhn.trelloclone.models.Card

open class CardListItemsAdapter(
    private val context: Context,
    private var list: ArrayList<Card>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    class MyViewHolder(binding: ItemCardBinding): RecyclerView.ViewHolder(binding.root){
        val viewLabelColor = binding.viewLabelColor
        val tvCardName = binding.tvCardName
        val rvCardSelectedMembersList = binding.rvCardSelectedMembersList

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardListItemsAdapter.MyViewHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {

//            if (model.labelColor.isNotEmpty()) {
//                holder.viewLabelColor.visibility = View.VISIBLE
//                holder.viewLabelColor.setBackgroundColor(Color.parseColor(model.labelColor))
//            } else {
//                holder.viewLabelColor.visibility = View.GONE
//            }
//
//            holder.tvCardName.text = model.name

//            if ((context as TaskListActivity).mAssignedMembersDetailList.size > 0) {
//                // A instance of selected members list.
//                val selectedMembersList: ArrayList<SelectedMembers> = ArrayList()
//
//                // Here we got the detail list of members and add it to the selected members list as required.
//                for (i in context.mAssignedMembersDetailList.indices) {
//                    for (j in model.assignedTo) {
//                        if (context.mAssignedMembersDetailList[i].id == j) {
//                            val selectedMember = SelectedMembers(
//                                context.mAssignedMembersDetailList[i].id,
//                                context.mAssignedMembersDetailList[i].image
//                            )
//
//                            selectedMembersList.add(selectedMember)
//                        }
//                    }
//                }
//
//                if (selectedMembersList.size > 0) {
//
//                    if (selectedMembersList.size == 1 && selectedMembersList[0].id == model.createdBy) {
//                        holder.itemView.rv_card_selected_members_list.visibility = View.GONE
//                    } else {
//                        holder.itemView.rv_card_selected_members_list.visibility = View.VISIBLE
//
//                        holder.itemView.rv_card_selected_members_list.layoutManager =
//                            GridLayoutManager(context, 4)
//                        val adapter = CardMemberListItemsAdapter(context, selectedMembersList, false)
//                        holder.itemView.rv_card_selected_members_list.adapter = adapter
//                        adapter.setOnClickListener(object :
//                            CardMemberListItemsAdapter.OnClickListener {
//                            override fun onClick() {
//                                if (onClickListener != null) {
//                                    onClickListener!!.onClick(position)
//                                }
//                            }
//                        })
//                    }
//                } else {
//                    holder.itemView.rv_card_selected_members_list.visibility = View.GONE
//                }
//            }
//
//            holder.itemView.setOnClickListener {
//                if (onClickListener != null) {
//                    onClickListener!!.onClick(position)
//                }
//            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onClick(cardPosition: Int)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

}