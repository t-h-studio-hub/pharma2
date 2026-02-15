package com.spinex.pharma

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MapActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val location = intent.getStringExtra(EXTRA_LOCATION).orEmpty()
        val mapWebView: WebView = findViewById(R.id.mapWebView)

        mapWebView.webViewClient = WebViewClient()
        mapWebView.settings.javaScriptEnabled = true
        mapWebView.settings.domStorageEnabled = true

        val query = URLEncoder.encode(location, StandardCharsets.UTF_8.toString())
        val mapUrl = "https://www.google.com/maps/search/?api=1&query=$query"
        mapWebView.loadUrl(mapUrl)
    }

    companion object {
        const val EXTRA_LOCATION = "extra_location"
    }
}