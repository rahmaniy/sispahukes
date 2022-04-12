package com.example.sispakhukumkesehatan.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.sispakhukumkesehatan.Extra
import com.example.sispakhukumkesehatan.R
import kotlinx.android.synthetic.main.fragment_pilihan_a1.*

class PilihanA1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pilihan_a1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pertanyaanA1List = ArrayList<String>()

        btn_proses1.setOnClickListener {
            if (cb_s01.isChecked ){
                pertanyaanA1List.add("s01")
            }
            if (cb_s02.isChecked ){
                if (cb_t2.isChecked || cb_t3.isChecked || cb_t4.isChecked){
                    pertanyaanA1List.add("s02a")
                }
                else {
                    pertanyaanA1List.add("s02")
                }
            }
            if (cb_s03.isChecked){
                pertanyaanA1List.add("s03")
            }
            if (cb_s04.isChecked){
                pertanyaanA1List.add("s04")
            }
            if (cb_s05.isChecked){
                pertanyaanA1List.add("s05")
            }
            if (cb_s06.isChecked){
                pertanyaanA1List.add("s06")
            }
            if (cb_s09.isChecked){
                pertanyaanA1List.add("s09")
            }
            if (cb_s11.isChecked){
                if (cb_t2.isChecked || cb_t3.isChecked || cb_t4.isChecked){
                    pertanyaanA1List.add("s11a")
                }
            }

            val mPertanyaanA1Fragment = PertanyaanA1Fragment()

            mPertanyaanA1Fragment.arguments = Bundle()
            mPertanyaanA1Fragment.arguments!!.putStringArrayList(Extra, pertanyaanA1List)

            val mFragmentManager = fragmentManager as FragmentManager
            mFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, mPertanyaanA1Fragment, PertanyaanA1Fragment::class.java.simpleName)
                //.addToBackStack(null)
                .commit()
        }
    }
}