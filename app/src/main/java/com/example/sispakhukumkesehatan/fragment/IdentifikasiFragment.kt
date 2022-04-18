package com.example.sispakhukumkesehatan.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.activity.IdentifikasiActivity
import com.example.sispakhukumkesehatan.activity.KonsultasiActivity
import kotlinx.android.synthetic.main.fragment_identifikasi.*

class IdentifikasiFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_identifikasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_mulai_identifikasi.setOnClickListener {
            val intent = Intent(context, IdentifikasiActivity::class.java)
            startActivity(intent)
        }
        btn_konsultasi.setOnClickListener {
            val intent = Intent(context, KonsultasiActivity::class.java)
            startActivity(intent)
        }
    }
}
