package com.example.sispakhukumkesehatan.adapter

import android.view.View
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.model.SengketaModel
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_row_child.view.*

open class SengketaAdapterChild(val sengketaModel: SengketaModel) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tv_child.text = sengketaModel.deskripsi
    }

    override fun getLayout(): Int {
        return R.layout.item_row_child
    }
}