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
import com.example.sispakhukumkesehatan.adapter.LawAdapterChild
import com.example.sispakhukumkesehatan.model.MainModel
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_hasil.progress_bar_hasil
import kotlinx.android.synthetic.main.activity_hasil_identifikasi.*

class HasilIdentifikasiActivity : AppCompatActivity() {
    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_identifikasi)

        val pasalHasilIdentifikasi = intent.getParcelableArrayListExtra<MainModel>(Extra)
        val hukumanHasilIdentifikasi = intent.getParcelableArrayListExtra<MainModel>(LIST_HUKUMAN)
        val pelaku = intent.getStringExtra(PELAKU)
        val hasilKasus = intent.getStringExtra(KASUS)
        val listPasal : MutableList<MainModel> = mutableListOf()
        val listHukuman : MutableList<MainModel> = mutableListOf()
        val listHukumanSama : MutableList<String> = mutableListOf()

        val sanksiAdministratif = "\na. teguran lisan;" +
                "\nb. peringatan tertulis;" +
                "\nc. denda administratif; dan/atau" +
                "\nd. pencabutan izin."
        val pidanaTambahan = "\na. pencabutan izin usaha; dan/atau" +
                "\nb. pencabutan status badan hukum."

        tv_hasil_identifikasi.text = "Sanksi yang dijatuhkan kepada " + pelaku.toLowerCase() +
                " yang diduga " + hasilKasus

        for (i in 0 until pasalHasilIdentifikasi.size) {
            var x = 0
            if (hukumanHasilIdentifikasi[i].name == "p06") {
                hukumanHasilIdentifikasi[i].value += sanksiAdministratif
            } else if (
                hukumanHasilIdentifikasi[i].name == "p11"
                || hukumanHasilIdentifikasi[i].name == "p18"
                || hukumanHasilIdentifikasi[i].name == "p19"
                || hukumanHasilIdentifikasi[i].name == "p20"
                || hukumanHasilIdentifikasi[i].name == "p21"
                || hukumanHasilIdentifikasi[i].name == "p22"
                || hukumanHasilIdentifikasi[i].name == "p23"
            ) {
                hukumanHasilIdentifikasi[i].value += pidanaTambahan
            }

            if (listPasal.size == 0) {
                listPasal.add(pasalHasilIdentifikasi[i])
                listHukuman.add(hukumanHasilIdentifikasi[i])
            } else {
                for (j in 0 until listPasal.size) {
                    var y = 0
                    if (pasalHasilIdentifikasi[i].name == listPasal[j].name) {
                        if (listHukumanSama.size == 0) {
                            if (hukumanHasilIdentifikasi[i].name == listHukuman[j].name) {
                                x = 1
                            } else {
                                listHukuman[j].value += "\n\n${hukumanHasilIdentifikasi[i].value}"
                                listHukumanSama.add(listHukuman[j].name)
                                listHukumanSama.add(hukumanHasilIdentifikasi[i].name)
                                x = 1
                            }
                        } else {
                            for (k in 0 until listHukumanSama.size) {
                                if (hukumanHasilIdentifikasi[i].name == listHukumanSama[k]) {
                                    x = 1
                                    y = 1
                                }
                            }
                            if (y == 0) {
                                listHukuman[j].value += "\n\n${hukumanHasilIdentifikasi[i].value}"
                                listHukumanSama.add(hukumanHasilIdentifikasi[i].name)
                                x = 1
                            }
                        }
                    }
                }
                if (x == 0) {
                    listPasal.add(pasalHasilIdentifikasi[i])
                    listHukuman.add(hukumanHasilIdentifikasi[i])
                }
            }
        }

        adapter.apply {
            clear()

            for (i in 0 until listPasal.size) {
                add(ExpandableGroup(LawAdapter(listPasal[i])).apply {
                    add(LawAdapterChild(listHukuman[i]))
                    notifyDataSetChanged()
                })
                notifyDataSetChanged()
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

    companion object {
        val PELAKU = "PELAKU"
        val KASUS = "KASUS"
        val LIST_HUKUMAN = "LIST_HUKUMAN"
    }
}