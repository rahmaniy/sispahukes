package com.example.sispakhukumkesehatan.adapter

import android.view.View
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.model.SengketaModel
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_row.view.*
import kotlinx.android.synthetic.main.item_row_parent.view.*

class SengketaAdapter(val sengketaModel: SengketaModel) : Item<ViewHolder>(), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tv_parent.text = sengketaModel.isi
        viewHolder.itemView.iv_indicator.setOnClickListener {
            expandableGroup.onToggleExpanded()
            changeStuff(viewHolder)
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_row_parent
    }

    private fun changeStuff(viewHolder: ViewHolder) {
        viewHolder.itemView.iv_indicator.apply {
            setImageResource(
                if (expandableGroup.isExpanded) {
                    R.drawable.ic_baseline_keyboard_arrow_up_24
                } else {
                    R.drawable.ic_baseline_keyboard_arrow_down_24
                }
            )
        }
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }
}