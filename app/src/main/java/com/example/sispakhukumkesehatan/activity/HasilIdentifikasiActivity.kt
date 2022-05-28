package com.example.sispakhukumkesehatan.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.sispakhukumkesehatan.Extra
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.adapter.LawAdapter
import com.example.sispakhukumkesehatan.model.LawModel
import com.example.sispakhukumkesehatan.model.MainModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_hasil.progress_bar_hasil
import kotlinx.android.synthetic.main.activity_hasil_identifikasi.*

class HasilIdentifikasiActivity : AppCompatActivity() {
    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_identifikasi)

        val hasilIdentifikasi = intent.getParcelableArrayListExtra<LawModel>(Extra)
        val pelaku = intent.getStringExtra("Pelaku")
        val hasilKasus = intent.getStringExtra("Kasus")
        val listPasal : MutableList<LawModel> = mutableListOf()

        tv_hasil_identifikasi.text = "Sanksi yang dijatuhkan kepada " + pelaku.toLowerCase() +
                " yang diduga\n\n" + hasilKasus

        adapter.clear()
        for (i in 0 until hasilIdentifikasi.size){
            var x = 0
            if (listPasal.size == 0){
                listPasal.add(hasilIdentifikasi[i])
                adapter.add(LawAdapter(hasilIdentifikasi[i]))
                adapter.notifyDataSetChanged()
            } else {
                for (j in 0 until listPasal.size){
                    if (hasilIdentifikasi[i].name == listPasal[j].name){
                        x = 1
                    }
                }
                if (x == 0) {
                    listPasal.add(hasilIdentifikasi[i])
                    adapter.add(LawAdapter(hasilIdentifikasi[i]))
                    adapter.notifyDataSetChanged()
                }
            }
        }
        recylce_hasil_identifikasi.adapter = adapter
        progress_bar_hasil.visibility = View.INVISIBLE
        setActionBarTitle("Hasil Identifikasi")
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}