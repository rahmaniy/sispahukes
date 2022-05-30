package com.example.sispakhukumkesehatan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MainModel(val name: String, var value: String): Parcelable {
    constructor():this("","")
}