package com.example.sispakhukumkesehatan.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.fragment.PilihSengketaFragment

class KonsultasiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identifikasi)

        val mFragmenManager = supportFragmentManager

        val mPilihSengketaFragment = PilihSengketaFragment()
        val fragment = mFragmenManager.findFragmentByTag(PilihSengketaFragment::class.java.simpleName)

        if (fragment !is PilihSengketaFragment) {
            mFragmenManager
                .beginTransaction()
                .add(R.id.frame_container, mPilihSengketaFragment, PilihSengketaFragment::class.java.simpleName)
                .commit()
        }

        setActionBarTitle("Konsultasi Sengketa Medis")
    }
    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }
}