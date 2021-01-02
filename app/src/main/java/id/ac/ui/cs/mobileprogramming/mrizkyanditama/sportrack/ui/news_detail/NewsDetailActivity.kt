package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.news_detail

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_detail)
        webView = findViewById(R.id.webview)
        webView.settings.setJavaScriptEnabled(true)

        val urlNews = intent.getStringExtra("URL_NEWS")

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        if (urlNews != null) {
            webView.loadUrl(urlNews)
        }
    }
}