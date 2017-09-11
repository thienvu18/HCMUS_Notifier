package thienvu.hcmusnotifier

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_web_view.*
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.graphics.Bitmap
import android.content.ContentValues.TAG
import android.net.http.SslError
import android.util.Log
import android.view.KeyEvent
import android.webkit.SslErrorHandler
import android.webkit.WebResourceResponse
import android.webkit.HttpAuthHandler
import android.webkit.ClientCertRequest






class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webSettings = wv_notify.settings
        webSettings.javaScriptEnabled = true
        webSettings.builtInZoomControls = true

        val intent = intent
        wv_notify.loadUrl(intent.getStringExtra("url"))
        wv_notify.webViewClient = WebViewClient()
    }
}
