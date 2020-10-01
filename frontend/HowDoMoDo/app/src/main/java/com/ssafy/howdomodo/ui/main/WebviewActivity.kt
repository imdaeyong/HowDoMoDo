package com.ssafy.howdomodo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ssafy.howdomodo.R
import kotlinx.android.synthetic.main.activity_web_view.*
import java.lang.reflect.Proxy

class WebviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url = intent.getStringExtra("url")
        wb_page.webViewClient = WebViewClient()
        val webViewSettings = wb_page.settings
        webViewSettings.run {
            javaScriptEnabled = true
            setSupportMultipleWindows(false)
            useWideViewPort = true
            setSupportZoom(false)
        }
        Log.e("test",url!!)
        wb_page.loadUrl(url!!)
    }
}