package com.example.roman.testproject.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.roman.testproject.R
import kotlinx.android.synthetic.main.pdf_web_view_activity.*

class PdfReaderActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdf_web_view_activity)
        web_view.settings.javaScriptEnabled = true
        val intent = getIntent()
        val string = intent.getStringExtra("key")
        web_view.loadUrl("http://docs.google.com/gview?embedded=true&url=" + string)
        web_view.webViewClient = MyWebViewClient()
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}