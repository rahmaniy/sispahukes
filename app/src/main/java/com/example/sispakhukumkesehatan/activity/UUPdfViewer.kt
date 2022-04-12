package com.example.sispakhukumkesehatan.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.sispakhukumkesehatan.R
import com.github.barteksc.pdfviewer.PDFView

class UUPdfViewer : AppCompatActivity() {

    companion object {
        const val EXTRA_PDF = "extra_pdf"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.uu_pdf_viewer)

        val extPdf = intent.getStringExtra(EXTRA_PDF)

        val pdfViewer: PDFView = findViewById(R.id.pdf_viewer)
        pdfViewer.fromAsset(extPdf)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .load()

        setActionBarTitle("Undang-Undang")
    }
    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }
}
