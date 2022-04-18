package com.example.sispakhukumkesehatan.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.adapter.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null
    private var sectionPagerAdapter: SectionPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolbar)

        sectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int){
        when (selectedMode) {
            R.id.action_tentang_aplikasi -> {
                val moveTentangAplikasi = Intent(this, TentangAplikasi::class.java)
                startActivity(moveTentangAplikasi)
            }

            R.id.action_bantuan -> {
                val moveBantuan = Intent(this, Bantuan::class.java)
                startActivity(moveBantuan)
            }
        }
    }

    override fun onBackPressed() {
        finishAffinity()
        finish()
    }
}
