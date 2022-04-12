package com.example.sispakhukumkesehatan.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import com.example.sispakhukumkesehatan.Extra
import com.example.sispakhukumkesehatan.R
import com.example.sispakhukumkesehatan.adapter.*
import com.example.sispakhukumkesehatan.fragment.DugaanKasusFragment
import com.example.sispakhukumkesehatan.model.KasusModel
import com.example.sispakhukumkesehatan.model.LawModel
import com.example.sispakhukumkesehatan.model.SengketaModel
import com.google.firebase.database.*
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_hasil.*
import kotlinx.android.synthetic.main.fragment_pertanyaan_kasus.*
import kotlinx.android.synthetic.main.fragment_pilih_sengketa.*

class HasilKonsultasiActivity : AppCompatActivity() {
    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)
        val hasilDugaan = intent.getParcelableExtra<KasusModel>(Extra)
        tv_hasil_kasus_catatan.visibility = View.GONE

        val refDB = FirebaseDatabase.getInstance().getReference()
        refDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val pasal = snapshot.child("sanksi").child(hasilDugaan.kode).child("pasal").value
                val denda = snapshot.child("sanksi").child(hasilDugaan.kode).child("denda").value
                val dugaan = snapshot.child("sanksi").child(hasilDugaan.kode).child("dugaan").value
                val pelaku = snapshot.child("sanksi").child(hasilDugaan.kode).child("pelaku").value
                val penjaraAtauKurungan = snapshot.child("sanksi").child(hasilDugaan.kode).child("penjaraAtauKurungan").value

                tampilHasil(hasilDugaan.kode, pasal.toString(), denda.toString(), dugaan.toString(), pelaku.toString(), penjaraAtauKurungan.toString())
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        progress_bar_hasil.visibility = View.INVISIBLE
        setActionBarTitle("Hasil Konsultasi")
    }

    fun tampilHasil(kodeHasilDugaan: String, pasalKasusMPD1: String, sanksiDenda: String, dugaan: String, pelaku: String, sanksiPenjaraAtauKurungan: String) {
        //variabel pelanggaran disiplin profesi (PDP)
        val hasilKasusPDP = "Sanksi yang dijatuhkan kepada dokter atau dokter gigi yang diduga " +
                "melakukan pelanggaran disiplin profesi, menurut Peraturan Konsil Kedokteran " +
                "Indonesia Nomor 50 Tahun 2017 adalah sbb.:"

        val sanksiPDP = "1. Pemberian Peringatan Tertulis, \n\n" +
                "2. Rekomendasi pencabutan Surat Tanda Registrasi (STR) untuk sementara waktu, " +
                "paling lama 1 tahun atau untuk selamanya, \n\n" +
                "3 Kewajiban mengikuti pendidikan atau rescholling di institusi pendidikan " +
                "kedokteran atau kedokteran gigi, atau pelatihan dilingkungan rumah sakit " +
                "pendidikan atau wahana pendidikan"

        val penjelasanPDP = "Penjelasan:\n" +
                "Sanksi di atas ditentukan berdasarkan Putusan " +
                "Majelis Kehormatan Disiplin Kedokteran Indonesia (MKDKI)\n"

        val catatanPDP = "CATATAN:\n" +
                "Putusan MKDKI tidak menghilangkan hak pasien/keluarga pasien untuk mengajukan " +
                "gugatan perdata dan /atau tuntutan pidana secara bersamaan.\n" +
                "Meskipun Putusan MKDKI tidak bisa dijadikan sebagai alat bukti di pengadilan, " +
                "namun bisa menjadi dasar pertimbangan pihak yang dirugikan untuk menyelesaikan " +
                "dugaan malpraktik medis tersebut ke jalur hukum."

        //variabel malpraktek pidana UU No 29 Th 2004 (MPD1)
        var pelakuDalamParagraf = ""
        when (pelaku) {
            "1" -> pelakuDalamParagraf = "dokter atau dokter gigi"
            "2" -> pelakuDalamParagraf = "orang"
            "3" -> pelakuDalamParagraf = "pimpinan fasilitas pelayanan kesehatan"
            "4" -> pelakuDalamParagraf = "korporasi"
            "5" -> pelakuDalamParagraf = "dokter atau dokter gigi warga negara asing"
        }
        val hasilKasusMPD1 = "Sanksi yang dijatuhkan kepada " + pelakuDalamParagraf + " yang diduga " +
                dugaan + ", menurut " + pasalKasusMPD1 + " Undang-Undang Nomor 29 Tahun 2004 Tentang " +
                "Praktik Kedokteran adalah:"
        var sanksiMPD1 = "1. " + sanksiDenda + "\n\n" +
                "2. " + sanksiPenjaraAtauKurungan
        if (sanksiPenjaraAtauKurungan=="0") {
            sanksiMPD1 = sanksiDenda
        }

        //variabel malpraktek pidana KUHP 359 (MPD2)
        val hasilKasusMPD2 = "Sanksi yang dijatuhkan kepada dokter atau dokter gigi yang diduga " +
                "menyebabkan mati atau cedera berat, menurut Pasal 359 Kitab Undang-Undang Hukum Pidana " +
                "adalah:"

        val sanksiMPD2 = "Dihukum penjara selama-lamanya lima tahun atau kurungan selama-lamanya " +
                "satu tahun."

        //variabel penjelasan malpraktek pidana (MPD)
        val penjelasanMPD = "Penjelasan:\n" +
                "Berat atau ringannya sanksi di atas dijatuhkan, berdasar pada hasil pemeriksaan  " +
                "Majelis Hakim, yang kemudian ditetapkan dalam surat Putusan Pengadilan atau " +
                "Makmaham Agung."

        //variabel malpraktek perdata wanprestasi (MPR1)
        val hasilKasusMPR1 = "Sanksi yang dijatuhkan kepada dokter atau dokter gigi yang diduga " +
                "melakukan wanprestasi, menurut Pasal 1243 Kitab Undang-Undang Hukum Perdata " +
                "adalah:"

        val sanksiMPR1 = "Penggantian biaya, kerugian dan bunga karena tak dipenuhinya suatu perikatan mulai " +
                "diwajibkan, bila debitur, walaupun telah dinyatakan lalai, tetap lalai untuk " +
                "memenuhi perikatan itu, atau jika sesuatu yang harus diberikan atau dilakukannya " +
                "hanya dapat diberikan atau dilakukannya dalam waktu yang melampaui waktu yang " +
                "telah ditentukan."

        //variabel malpraktek perdata perbuatan melanggar hukum (MPR2)
        val hasilKasusMPR2 = "Sanksi yang dijatuhkan kepada dokter atau dokter gigi yang diduga " +
                "melakukan perbuatan melanggar hukum, menurut Pasal 1365 Kitab Undang-Undang Hukum Perdata " +
                "adalah:"

        val sanksiMPR2 = "Tiap perbuatan melanggar hukum, yang membawa kerugian kepada orang lain, " +
                "mewajibkan orang yang karena salahnya menerbitkan kerugian itu, mengganti kerugian tersebut."

        //variabel penjelasan malpraktek perdata (MPR)
        val penjelasanMPR = "Penjelasan:\n" +
                "Besar atau kecilnya ganti kerugian dijatuhkan, berdasar pada jumlah gugatan" +
                "beserta hasil pemeriksaan  Majelis Hakim, yang kemudian ditetapkan dalam surat " +
                "Putusan Pengadilan atau Makmaham Agung."

        //sanksi pelanggaran disiplin profesi
        for (i in 1..28) {
            if (i<10) {
                if (kodeHasilDugaan == ("pdp0"+i.toString())) {
                    tv_hasil_kasus_catatan.visibility = View.VISIBLE
                    tv_hasil_kasus.text = hasilKasusPDP
                    tv_sanksi.text = sanksiPDP
                    tv_hasil_kasus_penjelasan.text = penjelasanPDP
                    tv_hasil_kasus_catatan.text = catatanPDP
                }
            } else {
                if (kodeHasilDugaan == ("pdp"+i.toString())) {
                    tv_hasil_kasus_catatan.visibility = View.VISIBLE
                    tv_hasil_kasus.text = hasilKasusPDP
                    tv_sanksi.text = sanksiPDP
                    tv_hasil_kasus_penjelasan.text = penjelasanPDP
                    tv_hasil_kasus_catatan.text = catatanPDP
                }
            }
        }

        //sanksi malpraktik pidana UU No 29 Tahun 2004
        for (i in 1..15) {
            if (i<10) {
                if (kodeHasilDugaan == ("mpd10"+i.toString())) {
                    tv_hasil_kasus.text = hasilKasusMPD1
                    tv_sanksi.text = sanksiMPD1
                    tv_hasil_kasus_penjelasan.text = penjelasanMPD
                }
            } else {
                if (kodeHasilDugaan == ("mpd1"+i.toString())) {
                    tv_hasil_kasus.text = hasilKasusMPD1
                    tv_sanksi.text = sanksiMPD1
                    tv_hasil_kasus_penjelasan.text = penjelasanMPD
                }
            }
        }

        //sanksi malpraktik pidana KUHP 359
        //sanksi malpraktik perdata wanprestasi dan perbuatan melanggar hukum
        for (i in 1..4) {
            //sanksi malpraktik pidana KUHP 359
            if (kodeHasilDugaan == ("mpd2"+i.toString())) {
                tv_hasil_kasus.text = hasilKasusMPD2
                tv_sanksi.text = sanksiMPD2
                tv_hasil_kasus_penjelasan.text = penjelasanMPD
            }

            //sanksi malpraktik perdata wanprestasi
            if (kodeHasilDugaan == ("mpr1"+i.toString())) {
                tv_hasil_kasus.text = hasilKasusMPR1
                tv_sanksi.text = sanksiMPR1
                tv_hasil_kasus_penjelasan.text = penjelasanMPR
            }

            //sanksi malpraktik perdata perbuatan melanggar hukum
            if (kodeHasilDugaan == ("mpr2"+i.toString())) {
                tv_hasil_kasus.text = hasilKasusMPR2
                tv_sanksi.text = sanksiMPR2
                tv_hasil_kasus_penjelasan.text = penjelasanMPR
            }
        }
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }
}