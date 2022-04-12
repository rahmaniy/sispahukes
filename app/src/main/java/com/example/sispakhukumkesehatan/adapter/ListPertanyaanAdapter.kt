package com.example.sispakhukumkesehatan.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.model.PertanyaanModel

class ListPertanyaanAdapter(val context: Context,private val dataPertanyaan: List<PertanyaanModel>): RecyclerView.Adapter<ListPertanyaanAdapter.ListViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_pertanyaan, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataPertanyaan.size
    }
//
//    override fun getItemId(position: Long): Long {
//        return position
//    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

// override fun setHasStableIds(hasStableIds: Boolean) {
//        super.setHasStableIds(hasStableIds)
//    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind_pertanyaan(dataPertanyaan[position])
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvPertanyaan: TextView = itemView.findViewById(R.id.tv_pertanyaan)
        val rbiya = itemView.findViewById<RadioButton>(R.id.rb_iya)
        val rbtidak = itemView.findViewById<RadioButton>(R.id.rb_tidak)
        lateinit var pasal : String

        fun bind_pertanyaan(dataPertanyaan: PertanyaanModel){
            tvPertanyaan.text = dataPertanyaan.q
            pasal = dataPertanyaan.pasal.toString()
        }
    }
}