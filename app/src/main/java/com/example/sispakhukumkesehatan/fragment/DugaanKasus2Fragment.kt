package com.example.sispakhukumkesehatan.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sispakhukumkesehatan.Extra
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.activity.HasilKonsultasiActivity
import com.example.sispakhukumkesehatan.adapter.KasusAdapter
import com.example.sispakhukumkesehatan.model.KasusModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_dugaan_kasus2.*

class DugaanKasus2Fragment : Fragment() {
    private val adapter = GroupAdapter<ViewHolder>()
    private val dugaanKasusChildList: MutableList<KasusModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dugaan_kasus2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dugaanKasus = arguments!!.getParcelable<KasusModel>(Extra)

        val refDB = FirebaseDatabase.getInstance().getReference()
        refDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val getSengketaKode = snapshot.child(dugaanKasus.kode).child("kodeUtama").value
                val getSengketaIsi = snapshot.child("sengketa").child(getSengketaKode.toString()).child("isi").value

                tv_dugaan_kasus2.text = "Dugaan " + getSengketaIsi.toString() + " berdasarkan " + dugaanKasus.isi

                val cabangKasus = snapshot.child(dugaanKasus.kode).child("kasus")

                cabangKasus.children.forEach {
                    val isiCabangKasus = it.getValue(KasusModel::class.java)

                    dugaanKasusChildList.add((isiCabangKasus!!))

                    adapter.apply {
                        clear()
                        for (i in dugaanKasusChildList) {
                            add(KasusAdapter(i))
                            notifyDataSetChanged()
                        }
                        setOnItemClickListener { item, view ->
                            val itemCabangKasus = item as KasusAdapter
                            val intent = Intent(view.context, HasilKonsultasiActivity::class.java)
                            intent.putExtra(Extra, itemCabangKasus.kasusModel)
                            startActivity(intent)
                        }
                    }
                    recylce_dugaan_kasus2.adapter = adapter
                }

                progress_bar_dugaan_kasus2.visibility = View.INVISIBLE
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}