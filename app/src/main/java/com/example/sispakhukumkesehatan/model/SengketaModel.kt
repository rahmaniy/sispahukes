package com.example.sispakhukumkesehatan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SengketaModel(val deskripsi: String, val isi: String, val kode: String): Parcelable {
    constructor():this("","","")
}