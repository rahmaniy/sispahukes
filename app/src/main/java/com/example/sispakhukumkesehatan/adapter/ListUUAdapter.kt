package com.example.sispakhukumkesehatan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.model.UndangUndang

class ListUUAdapter(private val listUU: ArrayList<UndangUndang>): RecyclerView.Adapter<ListUUAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUU.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val uuKesehatan = listUU[position]
        holder.tvUndangUndang.text = uuKesehatan.undangUndang
        holder.tvNoUU.text = uuKesehatan.nomorUU

        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listUU[holder.adapterPosition])}
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUndangUndang: TextView = itemView.findViewById(R.id.tv_undang_undang)
        var tvNoUU: TextView = itemView.findViewById(R.id.tv_no_uu)
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: UndangUndang)
    }
}