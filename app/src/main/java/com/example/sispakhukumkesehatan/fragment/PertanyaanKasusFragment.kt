package com.example.sispakhukumkesehatan.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sispakhukumkesehatan.Extra
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.activity.HasilIdentifikasiActivity
import com.example.sispakhukumkesehatan.adapter.ItemRowButtonAdapter
import com.example.sispakhukumkesehatan.adapter.MainAdapter
import com.example.sispakhukumkesehatan.model.LawModel
import com.example.sispakhukumkesehatan.model.MainModel
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_pertanyaan_kasus.*

class PertanyaanKasusFragment : Fragment() {
    private val adapter = GroupAdapter<ViewHolder>()
    private val adapter2 = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pertanyaan_kasus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var klikBtn = 0
        var klikYesBtn = 0
        val listKasus : MutableList<MainModel> = mutableListOf()
        val listPasal : MutableList<MainModel> = mutableListOf()
        val listHukuman : MutableList<MainModel> = mutableListOf()
        val listPasalHukuman : MutableList<LawModel> = mutableListOf()

        val itemPasalHukuman = LawModel()
        val pelaku = arguments!!.getParcelable<MainModel>(Extra)
        val apakahPelaku = "Apakah " + pelaku?.value + " "
        val tandaTanya = "?"
        var damageAtauStatus = ""
        var hasilKasus = ""

        recylce_kasus2.visibility = View.GONE

        val refDB = FirebaseDatabase.getInstance().getReference()
        refDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val case = snapshot.child(pelaku.name).child("case")

                case.children.forEach {
                    val caseCode = it.key
                    val caseValue = snapshot.child("case").child(caseCode.toString()).getValue(MainModel::class.java)

                    listKasus.add(caseValue!!)

                    tv_kasus1.text = apakahPelaku + listKasus[klikBtn].value + tandaTanya + damageAtauStatus

                    btn_yes.setOnClickListener {
                        val caseDamage = case.child(listKasus[klikBtn].name).child("damage")
                        val caseStatus = case.child(listKasus[klikBtn].name).child("status")
                        val caseLaw = case.child(listKasus[klikBtn].name).child("law")

                        if (klikYesBtn == 0) {
                            hasilKasus = listKasus[klikBtn].value
                        } else if (klikYesBtn == 1) {
                            hasilKasus = "\n\n- $hasilKasus" +
                                    "\n- ${listKasus[klikBtn].value}"
                        } else if (klikYesBtn > 1) {
                            hasilKasus = hasilKasus + "\n- ${listKasus[klikBtn].value}"
                        }

                        klikYesBtn+=1

                        tv_kasus1.visibility = View.GONE
                        viewline1.visibility = View.GONE

                        if (listKasus.size-1 != klikBtn) {
                            if (caseDamage.hasChildren()) {
                                linear_layout_btn.visibility = View.GONE
                                damageAtauStatus = "\n\nMengakibatkan : "
                                adapter.clear()
                                adapter.add(MainAdapter(listKasus[klikBtn],apakahPelaku,tandaTanya,damageAtauStatus))
                                adapter.notifyDataSetChanged()
                                recylce_kasus.adapter = adapter
                                adapter2.clear()

                                caseDamage.children.forEach {
                                    val damageCode = it.key
                                    val damageLaw = it.child("law")
                                    val damageValue = snapshot.child("damage").child(damageCode.toString()).getValue(MainModel::class.java)

                                    recylce_kasus2.visibility = View.VISIBLE
                                    adapter2.add(ItemRowButtonAdapter(damageValue!!))
                                    adapter2.notifyDataSetChanged()
                                    recylce_kasus2.adapter = adapter2

                                    adapter2.setOnItemClickListener {item, view ->
                                        linear_layout_btn.visibility = View.VISIBLE
                                        recylce_kasus2.visibility = View.GONE

                                        damageLaw.children.forEach {
                                            val damageLawCode = it.key
                                            val damagePenaltyCode = it.child("penalty").value
                                            val damageLawValue = snapshot.child("law").child(damageLawCode.toString()).getValue(MainModel::class.java)
                                            val damagePenaltyValue = snapshot.child("penalty").child(damagePenaltyCode.toString()).getValue(MainModel::class.java)

                                            listPasal.add(damageLawValue!!)
                                            listHukuman.add(damagePenaltyValue!!)

                                            itemPasalHukuman.apply {
                                                nameLaw = listPasal[klikYesBtn-1].name
                                                valueLaw = listPasal[klikYesBtn-1].value
                                                namePenalty = listHukuman[klikYesBtn-1].name
                                                valuePenalty = listHukuman[klikYesBtn-1].value
                                            }

                                            listPasalHukuman.add(itemPasalHukuman)
//                                            Log.d("aktordb", listPasalHukuman[klikYesBtn-1].namePenalty)
                                        }

                                        val itemDamage = item as ItemRowButtonAdapter

                                        if (itemDamage.mainModel.name != "notd") {
                                            hasilKasus = hasilKasus + " mengakibatkan " + itemDamage.mainModel.value.toLowerCase()
                                        }

                                        klikBtn += 1

                                        adapter.clear()
                                        adapter.add(MainAdapter(listKasus[klikBtn],apakahPelaku,tandaTanya,""))
                                        adapter.notifyDataSetChanged()
                                        recylce_kasus.adapter = adapter
                                    }
                                }
                            } else if (caseStatus.hasChildren()) {
                                linear_layout_btn.visibility = View.GONE
                                damageAtauStatus = "\n\nStatus : "
                                adapter.clear()
                                adapter.add(MainAdapter(listKasus[klikBtn],apakahPelaku,tandaTanya,damageAtauStatus))
                                adapter.notifyDataSetChanged()
                                recylce_kasus.adapter = adapter
                                adapter2.clear()

                                caseStatus.children.forEach {
                                    val statusCode = it.key
                                    val statusLaw = it.child("law")
                                    val statusValue = snapshot.child("status").child(statusCode.toString()).getValue(MainModel::class.java)

                                    recylce_kasus2.visibility = View.VISIBLE
                                    adapter2.add(ItemRowButtonAdapter(statusValue!!))
                                    adapter2.notifyDataSetChanged()
                                    recylce_kasus2.adapter = adapter2

                                    adapter2.setOnItemClickListener {item, view ->
                                        linear_layout_btn.visibility = View.VISIBLE
                                        recylce_kasus2.visibility = View.GONE

                                        statusLaw.children.forEach {
                                            val statusLawCode = it.key
                                            val statusPenaltyCode = it.child("penalty").value
                                            val statusLawValue = snapshot.child("law").child(statusLawCode.toString()).getValue(MainModel::class.java)
                                            val statusPenaltyValue = snapshot.child("penalty").child(statusPenaltyCode.toString()).getValue(MainModel::class.java)

                                            listPasal.add(statusLawValue!!)
                                            listHukuman.add(statusPenaltyValue!!)

                                            itemPasalHukuman.apply {
                                                nameLaw = listPasal[klikYesBtn-1].name
                                                valueLaw = listPasal[klikYesBtn-1].value
                                                namePenalty = listHukuman[klikYesBtn-1].name
                                                valuePenalty = listHukuman[klikYesBtn-1].value
                                            }

                                            listPasalHukuman.add(itemPasalHukuman)
//                                            Log.d("aktordb", listPasalHukuman[klikYesBtn-1].nameLaw)
                                        }

                                        val itemStatus = item as ItemRowButtonAdapter

                                        hasilKasus = hasilKasus + " (${itemStatus.mainModel.value})"

                                        klikBtn += 1

                                        adapter.clear()
                                        adapter.add(MainAdapter(listKasus[klikBtn],apakahPelaku,tandaTanya,""))
                                        adapter.notifyDataSetChanged()
                                        recylce_kasus.adapter = adapter
                                    }
                                }
                            } else {
                                caseLaw.children.forEach {
                                    val lawCode = it.key
                                    val penaltyCode = it.child("penalty").value
                                    val lawValue = snapshot.child("law").child(lawCode.toString()).getValue(MainModel::class.java)
                                    val penaltyValue = snapshot.child("penalty").child(penaltyCode.toString()).getValue(MainModel::class.java)

                                    listPasal.add(lawValue!!)
                                    listHukuman.add(penaltyValue!!)

                                    itemPasalHukuman.apply {
                                        nameLaw = listPasal[klikYesBtn-1].name
                                        valueLaw = listPasal[klikYesBtn-1].value
                                        namePenalty = listHukuman[klikYesBtn-1].name
                                        valuePenalty = listHukuman[klikYesBtn-1].value
                                    }

                                    listPasalHukuman.add(itemPasalHukuman)
//                                    Log.d("aktordb", listPasalHukuman[klikYesBtn-1].namePenalty)
                                }

                                klikBtn += 1

                                adapter.clear()
                                adapter.add(MainAdapter(listKasus[klikBtn], apakahPelaku, tandaTanya,""))
                                adapter.notifyDataSetChanged()
                                recylce_kasus.adapter = adapter
                            }
                        } else {
                            if (caseDamage.hasChildren()) {
                                linear_layout_btn.visibility = View.GONE
                                damageAtauStatus = "\n\nMengakibatkan : "
                                adapter.clear()
                                adapter.add(MainAdapter(listKasus[klikBtn],apakahPelaku,tandaTanya,damageAtauStatus))
                                adapter.notifyDataSetChanged()
                                recylce_kasus.adapter = adapter
                                adapter2.clear()

                                caseDamage.children.forEach {
                                    val damageCode = it.key
                                    val damageLaw = it.child("law")
                                    val damageValue = snapshot.child("damage").child(damageCode.toString()).getValue(MainModel::class.java)

                                    recylce_kasus2.visibility = View.VISIBLE
                                    adapter2.add(ItemRowButtonAdapter(damageValue!!))
                                    adapter2.notifyDataSetChanged()
                                    recylce_kasus2.adapter = adapter2

                                    adapter2.setOnItemClickListener {item, view ->
                                        damageLaw.children.forEach {
                                            val damageLawCode = it.key
                                            val damagePenaltyCode = it.child("penalty").value
                                            val damageLawValue = snapshot.child("law").child(damageLawCode.toString()).getValue(MainModel::class.java)
                                            val damagePenaltyValue = snapshot.child("penalty").child(damagePenaltyCode.toString()).getValue(MainModel::class.java)

                                            listPasal.add(damageLawValue!!)
                                            listHukuman.add(damagePenaltyValue!!)

                                            itemPasalHukuman.apply {
                                                nameLaw = listPasal[klikYesBtn-1].name
                                                valueLaw = listPasal[klikYesBtn-1].value
                                                namePenalty = listHukuman[klikYesBtn-1].name
                                                valuePenalty = listHukuman[klikYesBtn-1].value
                                            }

                                            listPasalHukuman.add(itemPasalHukuman)
//                                            Log.d("aktordb", listPasalHukuman[klikYesBtn-1].namePenalty)
                                        }
//                                        Log.d("aktordb", listHasil.size.toString())

                                        val itemDamage = item as ItemRowButtonAdapter

                                        if (itemDamage.mainModel.name != "notd") {
                                            hasilKasus = hasilKasus + " mengakibatkan " + itemDamage.mainModel.value.toLowerCase()
                                        }

                                        val intent = Intent(context, HasilIdentifikasiActivity::class.java)
                                        intent.putParcelableArrayListExtra(Extra, ArrayList(listPasal))
                                        intent.putExtra("Pelaku", pelaku.value)
                                        intent.putExtra("Kasus", hasilKasus)
                                        startActivity(intent)
                                    }
                                }
                            } else if (caseStatus.hasChildren()) {
                                linear_layout_btn.visibility = View.GONE
                                damageAtauStatus = "\n\nStatus : "
                                adapter.clear()
                                adapter.add(MainAdapter(listKasus[klikBtn],apakahPelaku,tandaTanya,damageAtauStatus))
                                adapter.notifyDataSetChanged()
                                recylce_kasus.adapter = adapter
                                adapter2.clear()

                                caseStatus.children.forEach {
                                    val statusCode = it.key
                                    val statusLaw = it.child("law")
                                    val statusValue = snapshot.child("status").child(statusCode.toString()).getValue(MainModel::class.java)

                                    recylce_kasus2.visibility = View.VISIBLE
                                    adapter2.add(ItemRowButtonAdapter(statusValue!!))
                                    adapter2.notifyDataSetChanged()
                                    recylce_kasus2.adapter = adapter2

                                    adapter2.setOnItemClickListener {item, view ->
                                        statusLaw.children.forEach {
                                            val statusLawCode = it.key
                                            val statusPenaltyCode = it.child("penalty").value
                                            val statusLawValue = snapshot.child("law").child(statusLawCode.toString()).getValue(MainModel::class.java)
                                            val statusPenaltyValue = snapshot.child("penalty").child(statusPenaltyCode.toString()).getValue(MainModel::class.java)

                                            listPasal.add(statusLawValue!!)
                                            listHukuman.add(statusPenaltyValue!!)

                                            itemPasalHukuman.apply {
                                                nameLaw = listPasal[klikYesBtn-1].name
                                                valueLaw = listPasal[klikYesBtn-1].value
                                                namePenalty = listHukuman[klikYesBtn-1].name
                                                valuePenalty = listHukuman[klikYesBtn-1].value
                                            }

                                            listPasalHukuman.add(itemPasalHukuman)
//                                            Log.d("aktordb", listPasalHukuman[klikYesBtn-1].namePenalty)
                                        }
//                                        Log.d("aktordb", listHasil.size.toString())

                                        val itemStatus = item as ItemRowButtonAdapter

                                        hasilKasus = hasilKasus + " (${itemStatus.mainModel.value})"

                                        val intent = Intent(context, HasilIdentifikasiActivity::class.java)
                                        intent.putParcelableArrayListExtra(Extra, ArrayList(listPasal))
                                        intent.putExtra("Pelaku", pelaku.value)
                                        intent.putExtra("Kasus", hasilKasus)
                                        startActivity(intent)
                                    }
                                }
                            } else {
                                caseLaw.children.forEach {
                                    val lawCode = it.key
                                    val penaltyCode = it.child("penalty").value
                                    val lawValue = snapshot.child("law").child(lawCode.toString()).getValue(MainModel::class.java)
                                    val penaltyValue = snapshot.child("penalty").child(penaltyCode.toString()).getValue(MainModel::class.java)

                                    listPasal.add(lawValue!!)
                                    listHukuman.add(penaltyValue!!)

                                    itemPasalHukuman.apply {
                                        nameLaw = listPasal[klikYesBtn-1].name
                                        valueLaw = listPasal[klikYesBtn-1].value
                                        namePenalty = listHukuman[klikYesBtn-1].name
                                        valuePenalty = listHukuman[klikYesBtn-1].value
                                    }

                                    listPasalHukuman.add(itemPasalHukuman)
//                                    Log.d("aktordb", listPasalHukuman[klikYesBtn-1].namePenalty)
//                                    Log.d("aktordb", listHasil.size.toString())
                                }
//                                Log.d("aktordb", listHasil.size.toString())

                                val intent = Intent(context, HasilIdentifikasiActivity::class.java)
                                intent.putParcelableArrayListExtra(Extra, ArrayList(listPasal))
                                intent.putExtra("Pelaku",pelaku.value)
                                intent.putExtra("Kasus", hasilKasus)
                                startActivity(intent)
                            }
                        }
                    }

                    btn_no.setOnClickListener {
                        tv_kasus1.visibility = View.GONE
                        viewline1.visibility = View.GONE

                        if (listKasus.size-1 != klikBtn) {
                            klikBtn += 1

                            adapter.clear()
                            adapter.add(MainAdapter(listKasus[klikBtn], apakahPelaku, tandaTanya,""))
                            adapter.notifyDataSetChanged()
                            recylce_kasus.adapter = adapter
                        } else {
                            val intent = Intent(context, HasilIdentifikasiActivity::class.java)
                            intent.putParcelableArrayListExtra(Extra, ArrayList(listPasal))
                            intent.putExtra("Pelaku",pelaku.value)
                            intent.putExtra("Kasus", hasilKasus)
                            startActivity(intent)
                        }
                    }
                }
                progress_bar_kasus.visibility = View.INVISIBLE
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }
}