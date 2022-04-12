package com.example.sispakhukumkesehatan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class LawModel(val deskripsi: String, val name: String, val value: String): Parcelable {
    constructor():this("","","")
}