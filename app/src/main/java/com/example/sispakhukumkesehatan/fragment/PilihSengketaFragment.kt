package com.example.sispakhukumkesehatan.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sispakhukumkesehatan.Extra
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.adapter.ItemRowButtonAdapter
import com.example.sispakhukumkesehatan.adapter.SengketaAdapter
import com.example.sispakhukumkesehatan.adapter.SengketaAdapterChild
import com.example.sispakhukumkesehatan.model.SengketaModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_pilih_sengketa.*
import kotlinx.android.synthetic.main.item_row_child.*

class PilihSengketaFragment : Fragment() {
    private val adapter = GroupAdapter<ViewHolder>()
    private var sengketaList: MutableList<SengketaModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pilih_sengketa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val refDB = FirebaseDatabase.getInstance().getReference("sengketa")
        refDB.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val sengketa = snapshot.getValue(SengketaModel::class.java)

                sengketaList.add(sengketa!!)

                adapter.apply {
                    clear()
                    for (i in sengketaList) {
                        add(ExpandableGroup(SengketaAdapter(i)).apply {
                            add(SengketaAdapterChild(i))
                            notifyDataSetChanged()
                        })
                        notifyDataSetChanged()
                        setOnItemClickListener { item, view ->

                            val itemSengketa = item as SengketaAdapter
                            val mDugaanKasusFragment = DugaanKasusFragment()

                            mDugaanKasusFragment.arguments = Bundle()
                            mDugaanKasusFragment.arguments!!.putParcelable(Extra, itemSengketa.sengketaModel)

                            val mFragmentManager = fragmentManager as FragmentManager
                            mFragmentManager
                                .beginTransaction()
                                .replace(R.id.frame_container, mDugaanKasusFragment, DugaanKasusFragment::class.java.simpleName)
                                .addToBackStack(null)
                                .commit()

                            sengketaList.clear()
                            adapter.clear()
                        }
                    }
                }
                recylce_sengketa.adapter = adapter
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
    }
}