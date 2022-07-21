package com.joshhn.trelloclone.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.joshhn.trelloclone.adapters.LabelColorListItemsAdapter
import com.joshhn.trelloclone.databinding.DialogListBinding

abstract class LabelColorListDialog(context: Context,
                                    private var list: ArrayList<String>,
                                    private val title: String = "",
                                    private var mSelectedColor: String =""): Dialog(context) {

    private var adapter: LabelColorListItemsAdapter? = null
    private var binding: DialogListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState ?: Bundle())
        binding = DialogListBinding.inflate(layoutInflater)
        binding?.root?.let {
            setContentView(it)
        }
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView(){
        binding?.tvTitle?.text = title
        binding?.rvList?.layoutManager = LinearLayoutManager(context)
        adapter = LabelColorListItemsAdapter(context, list, mSelectedColor)
        binding?.rvList?.adapter = adapter

        adapter!!.onItemClickListener = object : LabelColorListItemsAdapter.OnItemClickListener {

            override fun onClick(position: Int, color: String) {
                dismiss()
                onItemSelected(color)
            }
        }
    }

    protected abstract fun onItemSelected(color: String)
}