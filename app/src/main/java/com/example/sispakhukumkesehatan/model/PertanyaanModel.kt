package com.example.sispakhukumkesehatan.model

import android.os.Parcel
import android.os.Parcelable


class PertanyaanModel(val q:String?, val pasal:String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(q)
        parcel.writeString(pasal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PertanyaanModel> {
        override fun createFromParcel(parcel: Parcel): PertanyaanModel {
            return PertanyaanModel(parcel)
        }

        override fun newArray(size: Int): Array<PertanyaanModel?> {
            return arrayOfNulls(size)
        }
    }
}