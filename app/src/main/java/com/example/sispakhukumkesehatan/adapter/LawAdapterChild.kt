package com.example.sispakhukumkesehatan.adapter

import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.model.MainModel
import com.example.sispakhukumkesehatan.model.SengketaModel
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_row_child.view.*

open class LawAdapterChild(val mainModel: MainModel) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tv_child.text = mainModel.value
    }

    override fun getLayout(): Int {
        return R.layout.item_row_child
    }
}