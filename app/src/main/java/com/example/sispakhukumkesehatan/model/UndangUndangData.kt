package com.example.sispakhukumkesehatan.model

object UndangUndangData {
    private val uu = arrayOf(
        "Undang-Undang tentang Praktik Kedokteran",
        "Undang-Undang tentang Kesehatan",
        "Undang-Undang tentang Rumah Sakit",
        "Undang-Undang tentang Tenaga Kesehatan",
        "Undang-Undang tentang Keperawatan",
        "Peraturan Menteri Kesehatan tentang Telemedicine"
    )

    private val noUU = arrayOf(
        "UU Nomor 29 Tahun 2004",
        "UU Nomor 36 Tahun 2009",
        "UU Nomor 44 Tahun 2009",
        "UU Nomor 36 Tahun 2014",
        "UU Nomor 38 Tahun 2014",
        "Permenkes Nomor 20 Tahun 2019"
    )
    private val uuPdf = arrayOf(
        "pdf_uu_praktik_kedokteran.PDF",
        "pdf_uu_kesehatan.pdf",
        "pdf_uu_rumah_sakit.PDF",
        "pdf_uu_tenaga_kesehatan.pdf",
        "pdf_uu_keperawatan.pdf",
        "pdf_permenkes_telemedicine.pdf"
    )

    val listData: ArrayList<UndangUndang>
    get() {
        val list = arrayListOf<UndangUndang>()
        for (position in uu.indices){
            val uuKes = UndangUndang()
            uuKes.undangUndang = uu[position]
            uuKes.nomorUU = noUU[position]
            uuKes.pdf = uuPdf[position]
            list.add(uuKes)
        }
        return list
    }
}
