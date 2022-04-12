package com.example.sispakhukumkesehatan.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.activity.UUPdfViewer
import com.example.sispakhukumkesehatan.adapter.ListUUAdapter
import com.example.sispakhukumkesehatan.model.UndangUndang
import com.example.sispakhukumkesehatan.model.UndangUndangData
import kotlinx.android.synthetic.main.fragment_undang_undang.*

class UndangUndangFragment : Fragment() {
    private var list: ArrayList<UndangUndang> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_undang_undang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_undang_undang.setHasFixedSize(true)

        list.addAll(UndangUndangData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rv_undang_undang.layoutManager = LinearLayoutManager(context)
        val listUUAdapter = ListUUAdapter(list)
        rv_undang_undang.adapter = listUUAdapter

        listUUAdapter.setOnItemClickCallback(object : ListUUAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UndangUndang) {
                showSelectedUU(data)
            }
        })
    }

    private fun showSelectedUU(undangUndang: UndangUndang){
        val movePdfViewer = Intent(context, UUPdfViewer::class.java)
        movePdfViewer.putExtra(UUPdfViewer.EXTRA_PDF, undangUndang.pdf)
        startActivity(movePdfViewer)
    }
}

