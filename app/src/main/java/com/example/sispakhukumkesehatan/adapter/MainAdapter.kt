package com.example.sispakhukumkesehatan.adapter

import android.view.View
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.model.MainModel
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_row2.view.*

class MainAdapter(val mainModel: MainModel, val apakahPelaku: String, val tandaTanya: String, val damageAtauStatus: String) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tv_item_row2.text = apakahPelaku + mainModel.value + tandaTanya + damageAtauStatus
    }

    override fun getLayout(): Int {
        return R.layout.item_row2
    }
}