package com.example.sispakhukumkesehatan.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.fragment.PilihanAktorFragment

class IdentifikasiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identifikasi)

        val mFragmenManager = supportFragmentManager

        val mPilihanAktorFragment = PilihanAktorFragment()
        val fragment = mFragmenManager.findFragmentByTag(PilihanAktorFragment::class.java.simpleName)

        if (fragment !is PilihanAktorFragment) {
            mFragmenManager
                .beginTransaction()
                .add(R.id.frame_container, mPilihanAktorFragment, PilihanAktorFragment::class.java.simpleName)
                .commit()
        }

        setActionBarTitle("Identifikasi Sengketa Medis")
    }
    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }
}