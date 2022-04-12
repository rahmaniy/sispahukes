package com.example.sispakhukumkesehatan.adapter

import android.view.View
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.model.MainModel
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_row.view.*
import kotlinx.android.synthetic.main.item_row2.view.*

class ItemRowButtonAdapter(val mainModel: MainModel) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tv_undang_undang.text = mainModel.value
        viewHolder.itemView.tv_no_uu.visibility = View.GONE
    }

    override fun getLayout(): Int {
        return R.layout.item_row
    }
}