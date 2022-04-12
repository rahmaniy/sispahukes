package com.example.sispakhukumkesehatan.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sispakhukumkesehatan.fragment.IdentifikasiFragment
import com.example.sispakhukumkesehatan.fragment.UndangUndangFragment

class SectionPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> return IdentifikasiFragment()
            1 -> return UndangUndangFragment()
        }
        return null!!
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Sengketa Medis"
            1 -> return "Undang-Undang"
        }
        return null
    }
}