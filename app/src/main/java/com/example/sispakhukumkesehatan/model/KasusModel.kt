package com.example.sispakhukumkesehatan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class KasusModel(val isi: String, val kode: String): Parcelable {
    constructor():this("","")
}