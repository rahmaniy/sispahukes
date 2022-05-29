package com.example.sispakhukumkesehatan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class LawModel(
    var nameLaw: String,
    var valueLaw: String,
    var namePenalty: String,
    var valuePenalty: String
    ): Parcelable {
    constructor():this("","", "","")
}