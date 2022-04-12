package com.example.sispakhukumkesehatan.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.sispakhukumkesehatan.Extra
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.adapter.ItemRowButtonAdapter
import com.example.sispakhukumkesehatan.adapter.MainAdapter
import com.example.sispakhukumkesehatan.model.MainModel
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_pilihan_aktor.*
import android.view.LayoutInflater as LayoutInflater1

class PilihanAktorFragment : Fragment() {
    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater1, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pilihan_aktor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val refDB = FirebaseDatabase.getInstance().getReference("actor")
        refDB.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val actor = snapshot.getValue(MainModel::class.java)
//                Log.d("aktordb",actor!!.value)

                adapter.add(ItemRowButtonAdapter(actor!!))

                adapter.setOnItemClickListener { item, view ->
                    val itemAktor = item as ItemRowButtonAdapter
                    val mPertanyaanKasusFragment = PertanyaanKasusFragment()

                    mPertanyaanKasusFragment.arguments = Bundle()
                    mPertanyaanKasusFragment.arguments!!.putParcelable(Extra, itemAktor.mainModel)

                    val mFragmentManager = fragmentManager as FragmentManager
                    mFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, mPertanyaanKasusFragment, PertanyaanKasusFragment::class.java.simpleName)
                        .addToBackStack(null)
                        .commit()

                    adapter.clear()
                }
                recylce_aktor.adapter = adapter
                progress_bar_aktor.visibility = View.INVISIBLE
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

//        btn_proses.setOnClickListener{
//            if(rb_a1.isChecked){
//                val mPilihanA1Fragment = PilihanA1Fragment()
//                val mFragmentManager = fragmentManager as FragmentManager
//                mFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.frame_container, mPilihanA1Fragment, PilihanA1Fragment::class.java.simpleName)
//                    //.addToBackStack(null)
//                    .commit()
//            }
//
//            else if(rb_a2.isChecked){
//                val mPilihanA2Fragment = PilihanA2Fragment()
//                val mFragmentManager = fragmentManager as FragmentManager
//                mFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.frame_container, mPilihanA2Fragment, PilihanA2Fragment::class.java.simpleName)
//                    //.addToBackStack(null)
//                    .commit()
//            }
//
//            else if(rb_a3.isChecked){
//                val mPilihanA3Fragment = PilihanA3Fragment()
//                val mFragmentManager = fragmentManager as FragmentManager
//                mFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.frame_container, mPilihanA3Fragment, PilihanA3Fragment::class.java.simpleName)
//                    //.addToBackStack(null)
//                    .commit()
//            }
//
//            else if(rb_a4.isChecked){
//                val mPilihanA4Fragment = PilihanA4Fragment()
//                val mFragmentManager = fragmentManager as FragmentManager
//                mFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.frame_container, mPilihanA4Fragment, PilihanA4Fragment::class.java.simpleName)
//                    //.addToBackStack(null)
//                    .commit()
//            }
//
//            else if(rb_a5.isChecked){
//                val mPilihanA5Fragment = PilihanA5Fragment()
//                val mFragmentManager = fragmentManager as FragmentManager
//                mFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.frame_container, mPilihanA5Fragment, PilihanA5Fragment::class.java.simpleName)
//                    //.addToBackStack(null)
//                    .commit()
//            }
//
//            else if(rb_a6.isChecked){
//                val mPilihanA6Fragment = PilihanA6Fragment()
//                val mFragmentManager = fragmentManager as FragmentManager
//                mFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.frame_container, mPilihanA6Fragment, PilihanA6Fragment::class.java.simpleName)
//                    //.addToBackStack(null)
//                    .commit()
//            }
//        }
    }
}