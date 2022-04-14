package com.example.sispakhukumkesehatan.adapter

import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.model.LawModel
import com.example.sispakhukumkesehatan.model.MainModel
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_row2.view.*

class LawAdapter(val lawModel: LawModel) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tv_item_row2.text = lawModel.value +"\n\n"+ lawModel.deskripsi
    }

    override fun getLayout(): Int {
        return R.layout.item_row2
    }
}