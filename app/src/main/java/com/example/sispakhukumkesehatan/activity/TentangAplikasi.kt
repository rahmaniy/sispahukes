package com.example.sispakhukumkesehatan.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.sispakhukumkesehatan.R
import kotlinx.android.synthetic.main.activity_tentang_aplikasi.*

class TentangAplikasi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentang_aplikasi)

        tv_dev.text = "Mahasiswa        \t\t\t\t\t\t\t: Noverina Rahmaniyanti" +
                "\nEmail                      \t\t\t\t\t: nrahmaniyanti@gmail.com" +
                "\nDosen Pembimbing I  \t: Dr. Eng. Admi Syarif" +
                "\nDosen Pembimbing II \t: Yulia Kusuma Wardani, S.H., L.L.M." +
                "\nDosen Pembahas       \t: Dr. Ir. Kurnia Muludi, M.S.Sc."

        setActionBarTitle("Tentang Aplikasi")
    }
    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }
}
