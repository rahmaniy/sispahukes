package com.example.sispakhukumkesehatan.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sispakhukumkesehatan.Extra
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.model.UndangUndang
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_pasal.*

class PasalFragment : Fragment() {
    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pasal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val pasalHasilList = arguments!!.getStringArrayList(Extra)
//        val pasalFix = ArrayList<String>()
//
//        for (i in 0 until pasalHasilList.size){
//            var x = 0
//
//            if (pasalFix.size == 0){
//                pasalFix.add(pasalHasilList[i])
//            }
//            else {
//                for (j in 0 until pasalFix.size){
//                    if (pasalHasilList[i] == pasalFix[j]){
//                        x = 1
//                    }
//                }
//                if (x == 0){
//                    pasalFix.add(pasalHasilList[i])
//                }
//            }
//        }
//
//        Log.d("newpas", pasalHasilList.toString())
//        rv_pasal.setHasFixedSize(true)
//        rv_pasal.layoutManager = LinearLayoutManager(context)
//        rv_pasal.adapter = PasalAdapter(pasalFix)
    }
}

//class PasalAdapter(private val pasalList: ArrayList<String>?): RecyclerView.Adapter<PasalAdapter.ListViewHolder>(){
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row, viewGroup, false)
//        return ListViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return pasalList?.size!!
//    }
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        holder.tvUndangUndang.text = pasalList?.get(position)!!
//        holder.tvNoUU.visibility = View.INVISIBLE
//    }
//
//    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var tvUndangUndang: TextView = itemView.findViewById(R.id.tv_undang_undang)
//        var tvNoUU: TextView = itemView.findViewById(R.id.tv_no_uu)
//    }
//}