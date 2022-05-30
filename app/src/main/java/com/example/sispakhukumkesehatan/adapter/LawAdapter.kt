package com.example.sispakhukumkesehatan.adapter

import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.model.MainModel
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_row_parent.view.*
import kotlinx.android.synthetic.main.item_row_parent_hasil_identifikasi.view.*
import kotlinx.android.synthetic.main.item_row_parent_hasil_identifikasi.view.iv_indicator

class LawAdapter(val mainModel: MainModel) : Item<ViewHolder>(), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tv_parent_hasil.text = mainModel.value
        viewHolder.itemView.setOnClickListener {
            expandableGroup.onToggleExpanded()
            changeStuff(viewHolder)
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_row_parent_hasil_identifikasi
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