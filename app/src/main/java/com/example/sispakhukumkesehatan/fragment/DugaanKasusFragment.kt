package com.example.sispakhukumkesehatan.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.sispakhukumkesehatan.Extra
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.activity.HasilKonsultasiActivity
import com.example.sispakhukumkesehatan.adapter.*
import com.example.sispakhukumkesehatan.model.KasusModel
import com.example.sispakhukumkesehatan.model.SengketaModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_dugaan_kasus.*

class DugaanKasusFragment : Fragment() {
    private val adapter = GroupAdapter<ViewHolder>()
    private val dugaanKasusList: MutableList<KasusModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dugaan_kasus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sengketa = arguments!!.getParcelable<SengketaModel>(Extra)
        var kataPertama = ""

        if (sengketa.kode != "apdp") {
            kataPertama = "Jenis "
        } else {
            kataPertama = "Dugaan "
        }

        tv_dugaan_kasus.text = kataPertama + sengketa.isi

        val refDB = FirebaseDatabase.getInstance().getReference()
        refDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val kasus = snapshot.child(sengketa.kode).child("kasus")

                kasus.children.forEach {
                    val isiKasus = it.getValue(KasusModel::class.java)
                    val kodeKasus = it.key

                    dugaanKasusList.add(isiKasus!!)

                    adapter.apply {
                        clear()
                        for (i in dugaanKasusList) {
                            add(KasusAdapter(i))
                            notifyDataSetChanged()
                        }
                        setOnItemClickListener { item, view ->
                            val itemKasus = item as KasusAdapter
                            when (sengketa.kode) {
                                "apdp" -> {
                                    val intent = Intent(view.context, HasilKonsultasiActivity::class.java)
                                    intent.putExtra(Extra, itemKasus.kasusModel)
                                    startActivity(intent)
                                }
                                else -> {
                                    val mDugaanKasus2Fragment = DugaanKasus2Fragment()

                                    mDugaanKasus2Fragment.arguments = Bundle()
                                    mDugaanKasus2Fragment.arguments!!.putParcelable(Extra, itemKasus.kasusModel)

                                    val mFragmentManager = fragmentManager as FragmentManager
                                    mFragmentManager
                                        .beginTransaction()
                                        .replace(R.id.frame_container, mDugaanKasus2Fragment, DugaanKasus2Fragment::class.java.simpleName)
                                        .addToBackStack(null)
                                        .commit()

                                    dugaanKasusList.clear()
                                    clear()
                                }
                            }

                        }
                    }
                    recylce_dugaan_kasus.adapter = adapter
                }
                progress_bar_dugaan_kasus.visibility = View.INVISIBLE
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}