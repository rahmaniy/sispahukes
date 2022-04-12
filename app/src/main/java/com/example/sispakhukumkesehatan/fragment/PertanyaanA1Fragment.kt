package com.example.sispakhukumkesehatan.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.persistableBundleOf
import androidx.fragment.app.FragmentManager
import com.example.sispakhukumkesehatan.Extra
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.adapter.ListPertanyaanAdapter
import com.example.sispakhukumkesehatan.model.PertanyaanA1Data
import com.example.sispakhukumkesehatan.model.PertanyaanModel
import kotlinx.android.synthetic.main.fragment_pertanyaan_a1.*

class PertanyaanA1Fragment : Fragment() {
    lateinit var adapter: ListPertanyaanAdapter
    private var listPertanyaan: ArrayList<PertanyaanModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pertanyaan_a1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pasalA1List = ArrayList<String?>()
        val cekSemua = ArrayList<String?>()
        val pertanyaanA1 = arguments!!.getStringArrayList(Extra)

        nextPertanyaanSebab.setOnClickListener {
            val data = getPasal(pasalA1List,cekSemua)
            if(data){
                if (cekSemua.size == listPertanyaan.size){
//                    Toast.makeText(context!!, pasalA1List.toString(), Toast.LENGTH_LONG).show()

                    val mPasalFragment = PasalFragment()

                    mPasalFragment.arguments = Bundle()
                    mPasalFragment.arguments!!.putStringArrayList(Extra, pasalA1List)

                    val mFragmentManager = fragmentManager as FragmentManager
                    mFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, mPasalFragment, PasalFragment::class.java.simpleName)
                        .commit()
                }
                else {
                    Toast.makeText(context, "pilih lagi", Toast.LENGTH_SHORT).show()
                    cekSemua.removeAll(cekSemua)
                    pasalA1List.removeAll(pasalA1List)
                }
            }
            else{
                Toast.makeText(context,"pilih",Toast.LENGTH_SHORT).show()
            }
        }

        for (i in 0 until pertanyaanA1.size){
            bindAdapter(pertanyaanA1[i])
            getPasal(pasalA1List,cekSemua)
        }

    }

    private fun getPasal(pasalA1List: ArrayList<String?>, cekSemua: ArrayList<String?>):Boolean {
        var itemcek = 0
        var hasil = false
        for (i in 0 until rv_pertanyaan_a1.childCount) {
            if (rv_pertanyaan_a1.findViewHolderForLayoutPosition(i) is ListPertanyaanAdapter.ListViewHolder) {
                val holder =
                    rv_pertanyaan_a1.findViewHolderForLayoutPosition(i) as ListPertanyaanAdapter.ListViewHolder

                if (holder.rbiya.isChecked) {
                    itemcek +=1
                    pasalA1List.add(holder.pasal)
                    cekSemua.add("2")
                }
                else if (holder.rbtidak.isChecked) {
                    itemcek +=1
                    cekSemua.add("2")
                }
            }
        }
        if(itemcek > 0 ){
            hasil = true
        }
        return hasil
    }

    fun bindAdapter(keyPertanyaan: String) {
        when (keyPertanyaan) {
            "s01" -> {
                listPertanyaan.addAll(PertanyaanA1Data.s01)
            }
            "s02" -> {
                listPertanyaan.addAll(PertanyaanA1Data.s02)
            }
            "s02a" -> {
                listPertanyaan.addAll(PertanyaanA1Data.s02a)
            }
            "s03" -> {
                listPertanyaan.addAll(PertanyaanA1Data.s03)
            }
            "s04" -> {
                listPertanyaan.addAll(PertanyaanA1Data.s04)
            }
            "s05" -> {
                listPertanyaan.addAll(PertanyaanA1Data.s05)
            }
            "s06" -> {
                listPertanyaan.addAll(PertanyaanA1Data.s06)
            }
            "s09" -> {
                listPertanyaan.addAll(PertanyaanA1Data.s09)
            }
            "s11a" -> {
                listPertanyaan.addAll(PertanyaanA1Data.s11a)
            }
        }
        adapter = ListPertanyaanAdapter(context!!, listPertanyaan)
        rv_pertanyaan_a1.adapter = adapter
    }
}